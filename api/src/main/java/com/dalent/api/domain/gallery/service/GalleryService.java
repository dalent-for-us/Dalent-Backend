package com.dalent.api.domain.gallery.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.gallery.dao.GalleryRepository;
import com.dalent.api.domain.gallery.domain.Gallery;
import com.dalent.api.domain.gallery.dto.GalleryInfoResponseDto;
import com.dalent.api.domain.gallery.exception.GalleryNotFoundException;
import com.dalent.api.domain.gallery.exception.NotMyGalleryException;
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

    public GalleryInfoResponseDto getGallery(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        this.makeNewGallery(user);
        Gallery gallery = galleryRepository.findByUser(user).orElseThrow(GalleryNotFoundException::new);

        return GalleryInfoResponseDto.builder()
                .gallery_id(gallery.getGalleryId())
                .banner_image(gallery.getBannerImage())
                .build();
    }

    private void makeNewGallery(User user) {
        if(!galleryRepository.findByUser(user).isPresent())
            galleryRepository.save(Gallery.builder().user(user).build());
    }

    public void updateBannerImage(String nickname, String bannerImage) {
        String clientName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!clientName.equals(nickname)) throw new NotMyGalleryException();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        this.makeNewGallery(user);
        Gallery gallery = galleryRepository.findByUser(user).orElseThrow(GalleryNotFoundException::new);
        gallery.changeBannerImage(bannerImage);
        galleryRepository.save(gallery);
    }
}
