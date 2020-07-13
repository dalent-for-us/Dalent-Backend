package com.dalent.api.domain.media.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MediaService {

    @Value("${local.file.path}")
    private String path;

    public String upload(MultipartFile file) throws Exception{
        String uuid = UUID.randomUUID().toString();
        int pos = file.getOriginalFilename().lastIndexOf(".");
        String ext = file.getOriginalFilename().substring( pos + 1);
        File dest = new File(path + uuid + "." + ext);
        file.transferTo(dest);
        return dest.getPath();
    }
}
