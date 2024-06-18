package com.mpmd.mi.event.consumer.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpmd.mi.event.consumer.exception.InValidInputException;
import com.mpmd.mi.event.consumer.model.ApiResponse;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.service.EventServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/event")
public class
EventController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EventController.class);
    @Autowired
    EventServiceImpl eventService;

    @GetMapping("/type")
    public String type(){
        return "WELCOME";
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addEvent(@RequestParam("eventDetails") String eventDetail, @RequestParam(name = "files", required = false) List<MultipartFile> multipartFiles)
            throws IOException {

        EventDetails details = new ObjectMapper().readValue(eventDetail,EventDetails.class);

        if(details.getEventID()==null || details.getEventName() == null) {
            logger.error("Either Event ID or Name is not provided",
                    new InValidInputException("Either Event ID or Name is not provided"));

            return ResponseEntity.badRequest().body(new ApiResponse("Event ID or Name is not provided",null,false));
        }
        if(ObjectUtils.isEmpty(multipartFiles)) {
            logger.debug("Files are not provided");
        }
        logger.info("Adding event");
        return ResponseEntity.ok(eventService.addEvent(details,multipartFiles));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCourseMaterial(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
        if(ObjectUtils.isEmpty(multipartFile)){
            logger.debug("File is missing");
            return ResponseEntity.badRequest().body("Please provide file to upload");
        }
        return ResponseEntity.ok(eventService.uploadFile(multipartFile));
    }

    @DeleteMapping("/deleteFile/{fileName}")
    public ResponseEntity<String> deleteCourseMaterial(@PathVariable("fileName") String fileName){
        eventService.deleteFile(fileName);
        return ResponseEntity.ok("Deleted the file "+fileName+ " successfully");
    }

    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<byte[]> downloadCourseMaterial(@PathVariable("fileName") String fileName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition","attachment; filename="+fileName);
        return ResponseEntity.ok().headers(headers).body(eventService.downloadFile(fileName));
    }

}


