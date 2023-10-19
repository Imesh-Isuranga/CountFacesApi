package com.imesh.facedetectapi.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FaceCountService {
    String getFaceCountUsingOnlyPath(String imagePath);
    String getFaceCountUsingImage(MultipartFile image) throws IOException;
}
