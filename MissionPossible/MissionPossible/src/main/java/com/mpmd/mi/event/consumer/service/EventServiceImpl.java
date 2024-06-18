package com.mpmd.mi.event.consumer.service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.mpmd.mi.event.consumer.controller.EventController;
import com.mpmd.mi.event.consumer.model.ApiResponse;
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
    public ApiResponse addEvent(EventDetails eventDetails, List<MultipartFile> multipartFiles) throws IOException {

        if(multipartFiles.size() != 0) {
            List<String> fileNames = new ArrayList<String>();
            for (MultipartFile file : multipartFiles) {
                logger.info("Adding file "+file.getOriginalFilename()) ;
                uploadFile(file);
                fileNames.add(file.getOriginalFilename());
                logger.info(file.getOriginalFilename()+ " added successfully to aws s3") ;
            }
            logger.info("Setting course material");
            eventDetails.setCourseMaterials(fileNames);
        }
        try{
            eventRepository.addEvent(eventDetails);
            logger.info("Event added successfully");
            return new ApiResponse(eventDetails.getEventID()+" Event is added successfully",
                    eventDetails,true);
        }
        catch (Exception e){
            logger.info("Event is not added");
            return new ApiResponse(eventDetails.getEventID()+" Event is not added",
                    eventDetails,false);
        }
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        logger.info("Converting file");
        File convertedFile = new File(multipartFile.getOriginalFilename());
//      Transferring content to the converted file
//      1. Using transferTo method in MultiPartFileInterface
//      multipartFile.transferTo(convertedFile);

//      2. Using FileOutputStream method
        FileOutputStream fos = new FileOutputStream(convertedFile);
        logger.info("Writing");
        fos.write(multipartFile.getBytes());
        fos.close();
        logger.debug("Storing object into s3 bucket");
        PutObjectResult putObjectResult = s3.putObject(bucketName,multipartFile.getOriginalFilename(),convertedFile);
        logger.debug("Storing object into s3 bucket");
        return putObjectResult.getContentMd5();
    }

    @Override
    public void deleteFile(String fileName) {
        logger.debug("Deleting... file from s3 bucket");
        s3.deleteObject(bucketName,fileName);
        logger.info("Deleted the file "+fileName);
    }

    @Override

    public byte[] downloadFile(String fileName){
        logger.debug("Getting object from s3 bucket");
        S3Object s3Object = s3.getObject(bucketName,fileName);
        try{
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
