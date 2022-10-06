package com.challengerFinal.arte.service.implement;

import com.challengerFinal.arte.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
@Service
public class FileServiceImplement implements FileService {

    @Override
    public String saveFile(MultipartFile file, String name, String directory) throws Exception {
        if(!file.getContentType().contains("image")) {
            return null;
        }
        Path rootFolder = Paths.get("src/main/resources/static/images/" + directory);
        String serverUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String fileName = name + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Files.copy(file.getInputStream(), rootFolder.resolve(fileName));
        return serverUrl + "/images/" + directory + "/" + fileName;
    }


    @Override
    public String updateFile(MultipartFile file, String name, String directory) throws Exception {
        String fileName = name + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path rootFolder = Paths.get("src/main/resources/static/images/" + directory);
        Files.deleteIfExists(rootFolder.resolve(fileName));
        return this.saveFile(file, name, directory);
    }

}
