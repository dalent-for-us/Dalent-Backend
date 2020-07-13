package com.dalent.api.domain.media.controller;

import com.dalent.api.domain.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/files")
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestPart MultipartFile file) throws Exception {
        return mediaService.upload(file);
    }
}
