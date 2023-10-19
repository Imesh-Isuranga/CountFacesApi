package com.imesh.facedetectapi.Services;

import com.imesh.facedetectapi.config.FaceCountConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class FaceCountServiceImpl implements FaceCountService {

    @Autowired
    FaceCountConfig faceCountConfig;

    String fileName = "1.jpg";
    @Override
    public String getFaceCountUsingOnlyPath(String imagePath) {
      /*  if(imagePath.contains("http")){
            try(InputStream in = new URL(imagePath).openStream()){
                Files.copy(in, Paths.get(resolvePythonScriptPath("assets")+File.separator+fileName));
                return getFaceCount(resolvePythonScriptPath("assets")+File.separator+fileName);
            } catch (IOException e) {
                return e+"";
            }
        }else{
            return getFaceCount(imagePath);
        }*/
        return getFaceCount("11");
    }

    private String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }

    private String getFaceCount(String path) {
        String faceCount = "0";
        try {
            try {
                System.out.println(resolvePythonScriptPath("GetFacesCount.py"));
                System.out.println(resolvePythonScriptPath("assets"));
                System.out.println(System.getProperty("user.dir"));
            }catch (Exception e){
                System.out.println("Erorrrr  " + e);
            }





            boolean isPythonInstalled = false;
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("python", "--version");
                Process process = processBuilder.start();
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    isPythonInstalled = true;
                }
            } catch (IOException | InterruptedException e) {
                // Python is not installed or an error occurred
            }


            System.out.println("---------------------------------------------------  " + isPythonInstalled);




            ProcessBuilder processBuilder = new ProcessBuilder("python",System.getProperty("user.dir")+File.separator+"GetFacesCount.py");
            Process process = processBuilder.start();

            // Get the output stream to write data to Python
            OutputStream outputStream = process.getOutputStream();


            // Pass a string to the Python script
            outputStream.write("dfd".getBytes());
            outputStream.close();

            // Read the result from Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            faceCount = reader.readLine();

           // String currentDir = System.getProperty("user.dir");
           // Files.delete(Path.of(resolvePythonScriptPath("assets")+File.separator+fileName));


            // Wait for the Python process to complete
            if (process.waitFor(5, TimeUnit.SECONDS)) {
                System.out.println("Python method result: " + faceCount);
            } else {
                System.err.println("Python script execution timed out.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return faceCount;
    }

    @Override
    public String getFaceCountUsingImage(MultipartFile image) throws IOException {

    /*    fileName = image.getOriginalFilename();
        String path = (resolvePythonScriptPath("assets")+File.separator+fileName);
        File newImageFile = new File(path);
        image.transferTo(newImageFile);*/

        return getFaceCount("path");
    }



}
