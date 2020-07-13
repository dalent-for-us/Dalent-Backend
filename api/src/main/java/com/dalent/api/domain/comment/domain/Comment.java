package com.dalent.api.domain.comment.domain;

import com.dalent.api.domain.user.domain.User;
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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne
    @JoinColumn(name = "writer")
    private User writer;

    private String content;

    @OneToMany(mappedBy = "parent_comment")
    private List<Comment> childComment;

    @ManyToOne
    @JoinColumn(name = "parent_comment")
    private Comment parent_comment;

    @Builder
    public Comment(Work work, User writer, String content, Comment parentComment) {
        this.work = work;
        this.writer = writer;
        this.content = content;
        this.childComment = new ArrayList<>();
        this.parent_comment = parentComment;
    }

}
