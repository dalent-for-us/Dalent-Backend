package com.dalent.api.domain.comment.domain;

import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.domain.Work;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "child_comment")
    private Comment childComment;

    @Builder
    public Comment(Work work, User writer, String content) {
        this.work = work;
        this.writer = writer;
        this.content = content;
    }

}
