package com.dalent.api.domain.gallery.dao;

import com.dalent.api.domain.gallery.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
