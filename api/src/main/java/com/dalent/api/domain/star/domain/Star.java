package com.dalent.api.domain.star.domain;

import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.domain.Category;
import com.dalent.api.domain.work.domain.Work;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long starId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private User target;

    @Builder
    public Star(Category category, Work work, User user, User target) {
        this.category = category;
        this.work = work;
        this.user = user;
        this.target = target;
    }
}
