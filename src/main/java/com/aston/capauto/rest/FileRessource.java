package com.aston.capauto.rest;

import com.aston.capauto.CapAutoApplication;
import com.aston.capauto.domain.Eleve;
import com.aston.capauto.exception.ResourceNotFoundException;
import com.aston.capauto.payload.UploadFileResponse;

import com.aston.capauto.repository.EleveRepository;
import com.aston.capauto.service.FileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FileRessource {
    private static final Logger LOGGER = LogManager.getLogger(CapAutoApplication.class);
    private final FileStorageService fileStorageService;
    private final EleveRepository eleveRepository;

    public FileRessource(FileStorageService fileStorageService, EleveRepository eleveRepository) {
        this.fileStorageService = fileStorageService;
        this.eleveRepository = eleveRepository;
    }

    @PostMapping("file/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        final String fileName = this.fileStorageService.storeFile(file);
        final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("file/uploadedMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files).map(this::uploadFile).collect(Collectors.toList());
    }

    @GetMapping("file/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load a file Resource
        final Resource resource = this.fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (final IOException ex) {
            LOGGER.info("could not determine file type");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @PostMapping("file/upload/eleve/photo/{eleveId}")
    public Eleve uploadProfilePicture(@PathVariable(value = "eleveId") Long eleveId,
                                      @RequestParam("file") MultipartFile file) {
        return this.eleveRepository.findById(eleveId).map(eleve -> {
            final String fileName = this.fileStorageService.storeFile(file);

            final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/downloadFile/")
                    .path(fileName).toUriString();

            eleve.setPhoto(fileDownloadUri);
            return this.eleveRepository.save(eleve);
        }).orElseThrow(() -> new ResourceNotFoundException("eleve", "id", eleveId));
    }
}
