package com.dalent.api.domain.user.domain;

import com.dalent.api.domain.comment.domain.Comment;
import com.dalent.api.domain.follow.domain.Follow;
import com.dalent.api.domain.gallery.domain.Gallery;
import com.dalent.api.domain.work.domain.Work;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String nickname;

    private String profile_image;

    private int artStars;

    private int fashionStars;

    private int musicStars;

    private int programmingStars;

    private String introduce;

    @OneToOne(mappedBy = "user")
    private Gallery gallery;

    @OneToMany(mappedBy = "user")
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "target")
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Work> works = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.artStars = 0;
        this.fashionStars = 0;
        this.musicStars = 0;
        this.programmingStars = 0;
        this.introduce = "";
    }

}
