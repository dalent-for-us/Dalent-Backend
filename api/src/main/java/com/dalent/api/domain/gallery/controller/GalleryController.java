package com.dalent.api.domain.gallery.controller;

import com.dalent.api.domain.gallery.dto.GalleryInfoResponseDto;
import com.dalent.api.domain.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/gallery")
@RestController
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/{nickname}")
    public GalleryInfoResponseDto getGallery(@PathVariable("nickname") String nickname) {
        return galleryService.getGallery(nickname);
    }
}
