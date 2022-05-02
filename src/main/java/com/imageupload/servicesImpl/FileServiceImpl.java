package com.imageupload.servicesImpl;

import com.imageupload.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Accessing Filename
        String name= file.getOriginalFilename();

        //Full Path
        String filePath= path+ File.separator+name;

        //Create folder if not created
        File f=new  File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //File Copying
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }
}
