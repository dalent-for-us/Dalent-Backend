package com.dalent.api.domain.gallery.dao;

import com.dalent.api.domain.gallery.domain.Gallery;
import com.dalent.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByUser(User user);
}
