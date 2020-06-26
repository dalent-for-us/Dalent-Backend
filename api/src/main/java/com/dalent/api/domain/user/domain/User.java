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

    private int heartsCount;

    private String introduce;

    @OneToOne(mappedBy = "gallery")
    private Gallery gallery;

    @OneToMany(mappedBy = "following")
    private List<Follow> followings = new ArrayList<>();

    @OneToMany(mappedBy = "followers")
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "works")
    private List<Work> works = new ArrayList<>();

    @OneToMany(mappedBy = "comments")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.heartsCount = 0;
        this.introduce = "";
    }

}
