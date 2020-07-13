package com.dalent.api.domain.gallery.domain;

import com.dalent.api.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String bannerImage;

    @Builder
    public Gallery(User user) {
        this.user = user;
        this.bannerImage = null;
    }
}
