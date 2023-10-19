package com.imesh.facedetectapi.Controller;

import com.imesh.facedetectapi.Services.FaceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/facedetect")
@CrossOrigin
public class FaceCountController {


    @Autowired
    FaceCountService faceDetectService;

    @PostMapping("/get")
    public String getFaceCount(@RequestParam("image") MultipartFile image) throws IOException {
        String faceCount = faceDetectService.getFaceCountUsingImage(image);
        if(faceCount.equals("0")){
            return "There are no faces seems to be";
        }else{
            return "There are " +faceCount+" faces";
        }
    }

    @GetMapping("/get")
    public String getFaceCount(@RequestParam("imagePath") String imagePath){
        // Process imagePath and return the result
        String faceCount = faceDetectService.getFaceCountUsingOnlyPath(imagePath);
        if(faceCount.equals("0")){
            return "There are no faces seems to be";
        }else{
            return "There are " +faceCount+" faces";
        }
    }


}
