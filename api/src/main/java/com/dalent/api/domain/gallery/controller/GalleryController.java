package com.dalent.api.domain.gallery.controller;

import com.dalent.api.domain.gallery.dto.GalleryInfoResponseDto;
import com.dalent.api.domain.gallery.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping("/gallery")
@RestController
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/{nickname}")
    public GalleryInfoResponseDto getGallery(@PathVariable("nickname") String nickname) {
        return galleryService.getGallery(nickname);
    }

    @PutMapping("/{nickname}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void updateBannerImage(@PathVariable("nickname") String nickname,
                                  @RequestBody HashMap<String, String> banner_image) {
        galleryService.updateBannerImage(nickname, banner_image.get("banner_image"));
    }
}
