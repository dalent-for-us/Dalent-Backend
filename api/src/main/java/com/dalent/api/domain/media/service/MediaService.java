package com.dalent.api.domain.media.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@Service
public class MediaService {

    @Value("${local.file.path}")
    private String path;

    public String upload(MultipartFile file) throws Exception{
        String originalFileName = file.getOriginalFilename();
        File dest = new File(path + originalFileName);
        file.transferTo(dest);
        return dest.getPath();
    }
}
