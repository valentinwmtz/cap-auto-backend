package com.aston.capauto.service;

import com.aston.capauto.exception.FileNotFoundException;
import com.aston.capauto.exception.FileStorageException;
import com.aston.capauto.properties.FileStorageProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private static final Logger LOGGER = LogManager.getLogger(FileStorageService.class);
    private final Path FILE_STORAGE_LOCATION;

    public FileStorageService(FileStorageProperty fileStorageProperty) {
        this.FILE_STORAGE_LOCATION = Paths.get(fileStorageProperty.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.FILE_STORAGE_LOCATION);
        } catch (final Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded file will be stored", e);
        }
    }

    public String storeFile(MultipartFile file) {
        //Normalize file name
        final String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry ! Filename contains invalid path sequence" + fileName);
            }

            final Path targetLocation = this.FILE_STORAGE_LOCATION.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (final IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            final Path filePath = this.FILE_STORAGE_LOCATION.resolve(fileName).normalize();
            final Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (final MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
}
