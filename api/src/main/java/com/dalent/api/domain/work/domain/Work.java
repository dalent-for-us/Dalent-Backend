package com.dalent.api.domain.work.domain;

import com.dalent.api.domain.comment.domain.Comment;
import com.dalent.api.domain.user.domain.User;
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
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int stars;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    private String mediaLink;

    private String thumbnailImage;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @OneToMany(mappedBy = "work")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Work(Category category, String title, String content, MediaType mediaType, String mediaLink,
                String thumbnailImage, User author) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.mediaType = mediaType;
        this.mediaLink = mediaLink;
        this.thumbnailImage = thumbnailImage;
        this.author = author;
        this.stars = 0;
    }

    public void updateWork(String category, String title, String content, String mediaType, String mediaLink,
                      String thumbnailImage) {
        if(category != null) this.category = Category.valueOf(category.toUpperCase());
        if(title != null) this.title = title;
        if(content != null) this.content = content;
        if(mediaType != null) this.mediaType = MediaType.valueOf(mediaType.toUpperCase());
        if(mediaLink != null) this.mediaLink = mediaLink;
        if(thumbnailImage != null) this.thumbnailImage = thumbnailImage;
    }

}
