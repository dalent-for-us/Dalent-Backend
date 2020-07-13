package com.dalent.api.domain.gallery.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.gallery.dao.GalleryRepository;
import com.dalent.api.domain.gallery.domain.Gallery;
import com.dalent.api.domain.gallery.dto.GalleryInfoResponseDto;
import com.dalent.api.domain.gallery.exception.GalleryNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    private final UserRepository userRepository;

    public GalleryInfoResponseDto getMyGallery() {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        Gallery gallery;
        if(!galleryRepository.findByUser(user).isPresent()) {
            this.makeNewGallery(user);
        }
        gallery = galleryRepository.findByUser(user).orElseThrow(GalleryNotFoundException::new);
        return GalleryInfoResponseDto.builder()
                .gallery_id(gallery.getGalleryId())
                .banner_image(gallery.getBannerImage())
                .build();
    }

    private void makeNewGallery(User user) {
        galleryRepository.save(Gallery.builder().user(user).build());
    }
}
