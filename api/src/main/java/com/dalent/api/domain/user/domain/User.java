package com.dalent.api.domain.user.domain;

import com.dalent.api.domain.comment.domain.Comment;
import com.dalent.api.domain.follow.domain.Follow;
import com.dalent.api.domain.gallery.domain.Gallery;
import com.dalent.api.domain.star.domain.Star;
import com.dalent.api.domain.work.domain.Work;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User implements UserDetails {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false, unique = true)
    private String nickname;

    private String profile_image;

    private String introduce;

    private int artStarCounts;

    private int programmingStarCounts;

    private int fashionStarCounts;

    private int musicStarCounts;

    @OneToOne(mappedBy = "user")
    private Gallery gallery;

    @OneToMany(mappedBy = "target")
    private List<Star> stars;

    @OneToMany(mappedBy = "user")
    private List<Star> give_stars;

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
        this.introduce = "";
        this.profile_image = "";
        this.artStarCounts = 0;
        this.musicStarCounts = 0;
        this.programmingStarCounts = 0;
        this.fashionStarCounts = 0;
    }

    public void reviseInfo(String profile_image, String introduce) {
        if(profile_image != null) this.profile_image = profile_image;
        if(introduce != null) this.introduce = introduce;
    }

    public void changeStarCount(String category, int count) {
        switch (category) {
            case "ART": {
                this.artStarCounts += count;
                break;
            }
            case "MUSIC": {
                this.musicStarCounts += count;
                break;
            }
            case "PROGRAMMING": {
                this.programmingStarCounts += count;
                break;
            }
            case "FASHION": {
                this.fashionStarCounts += count;
                break;
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
