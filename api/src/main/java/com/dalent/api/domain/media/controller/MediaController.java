package com.dalent.api.domain.media.controller;

import com.dalent.api.domain.media.dto.UploadResponseDto;
import com.dalent.api.domain.media.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {

    private final S3Service s3Service;

    @PostMapping("/files")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadResponseDto upload(@RequestPart MultipartFile file) throws Exception {
        String contentType = file.getContentType().split("/")[0];
        String url = s3Service.upload(file);

        return UploadResponseDto.builder()
                .media_type(contentType)
                .file_link(url)
                .build();
    }
}
