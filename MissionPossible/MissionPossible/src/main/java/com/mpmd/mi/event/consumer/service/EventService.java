package com.mpmd.mi.event.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mpmd.mi.event.consumer.model.EventDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    public void addEvent(EventDetails eventDetails, List<MultipartFile> multipartFiles) throws IOException;
    public String uploadFile(MultipartFile file) throws IOException;
    public List<String> uploadFiles(List<MultipartFile> files) throws IOException;
    public void deleteFile(String fileName);
    public byte[] downloadFile(String fileName);


    }
