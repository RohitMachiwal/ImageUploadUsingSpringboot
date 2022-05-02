package com.imageupload.controller;

import com.imageupload.payload.FileResponse;
import com.imageupload.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    //project.image value used here
    @Value("${project.image}")
    private  String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile image){

        String fileName= null;
        try {
            fileName = fileService.uploadImage(path,image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null,"Image Uploading Error!!"), HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

        return new ResponseEntity<>(new FileResponse(fileName,"Image is successfully uploaded"), HttpStatus.OK);

    }
}
