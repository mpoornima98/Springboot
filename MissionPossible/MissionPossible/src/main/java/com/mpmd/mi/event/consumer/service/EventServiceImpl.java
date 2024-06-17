package com.mpmd.mi.event.consumer.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.mpmd.mi.event.consumer.controller.EventController;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.repository.EventRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;
    @Value("${cloud.aws.bucket-name}")
    private String bucketName;

    private final AmazonS3 s3;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EventController.class);


    public EventServiceImpl(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public void addEvent(EventDetails eventDetails, List<MultipartFile> multipartFiles) throws IOException {

        if(multipartFiles.size() != 0) {
            List<String> fileNames = new ArrayList<String>();
            for (MultipartFile file : multipartFiles) {
                logger.info("Adding file "+file.getOriginalFilename()) ;
                uploadFile(file);
                fileNames.add(file.getOriginalFilename());
                logger.info(file.getOriginalFilename()+ " added successfully to aws s3") ;

            }
            eventDetails.setCourseMaterials(fileNames);
        }

        eventRepository.addEvent(eventDetails);
    }


    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(multipartFile.getOriginalFilename());
//      Transferring content to the converted file
//      1. Using transferTo method in MultiPartFileInterface
//      multipartFile.transferTo(convertedFile);

//      2. Using FileOutputStream method
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        PutObjectResult putObjectResult = s3.putObject(bucketName,multipartFile.getOriginalFilename(),convertedFile);
        return putObjectResult.getContentMd5();
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        return null;
    }

    @Override
    public void deleteFile(String fileName) {
        s3.deleteObject(bucketName,fileName);
    }

    @Override
    public byte[] downloadFile(String fileName){
        S3Object s3Object = s3.getObject(bucketName,fileName);
        try{
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
