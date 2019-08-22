package ru.ryazan.senatorshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.payload.UploadFileResponse;
import ru.ryazan.senatorshop.core.service.ProductImageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DBFileController {


    @Autowired
    private ProductImageService DBFileStorageService;

//    @PostMapping("/uploadFileDB")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        ProductImage dbFile = DBFileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbFile.getId().toString())
//                .toUriString();
//
//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }

//    @PostMapping("/uploadMultipleFilesDB")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFileDB/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        ProductImage dbFile = DBFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getFileData()));
    }

}
