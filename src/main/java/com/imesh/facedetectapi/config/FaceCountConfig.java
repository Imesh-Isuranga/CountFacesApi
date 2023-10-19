package com.imesh.facedetectapi.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FaceCountConfig {
    private String dir;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
