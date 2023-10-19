package com.imesh.facedetectapi.Services;

import jakarta.transaction.Transactional;
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

    String fileName = "1.jpg";
    @Override
    public String getFaceCountUsingOnlyPath(String imagePath) {
        if(imagePath.contains("http")){
            try(InputStream in = new URL(imagePath).openStream()){
                Files.copy(in, Paths.get(System.getProperty("user.dir") + File.separator + "assets/" + fileName));
                return getFaceCount((System.getProperty("user.dir") + File.separator + "assets/" + fileName));
            } catch (IOException e) {
                return e+"";
            }
        }else{
            return getFaceCount(imagePath);
        }
    }

    private String resolvePythonScriptPath(String path){
        File file = new File(path);
        return file.getAbsolutePath();
    }

    private String getFaceCount(String path) {
        String faceCount = "0";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python",resolvePythonScriptPath("GetFacesCount.py"));
            Process process = processBuilder.start();

            // Get the output stream to write data to Python
            OutputStream outputStream = process.getOutputStream();


            // Pass a string to the Python script
            outputStream.write(path.getBytes());
            outputStream.close();

            // Read the result from Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            faceCount = reader.readLine();

            String currentDir = System.getProperty("user.dir");
            Files.delete(Path.of(System.getProperty("user.dir") + File.separator + "assets/" + fileName));


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

        fileName = image.getOriginalFilename();
        String path = (System.getProperty("user.dir") + File.separator + "assets/" + fileName);
        File newImageFile = new File(path);
        image.transferTo(newImageFile);

        return getFaceCount(path);
    }



}
