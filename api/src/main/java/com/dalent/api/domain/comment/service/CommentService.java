package com.dalent.api.domain.comment.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.comment.dao.CommentRepository;
import com.dalent.api.domain.comment.domain.Comment;
import com.dalent.api.domain.comment.dto.CreateCommentRequestDto;
import com.dalent.api.domain.comment.exception.CommentNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.dao.WorkRepository;
import com.dalent.api.domain.work.domain.Work;
import com.dalent.api.domain.work.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final WorkRepository workRepository;

    public long createComment(CreateCommentRequestDto request) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        Work work = workRepository.findById(Long.parseLong(request.getWork_id()))
                .orElseThrow(WorkNotFoundException::new);
        Comment parent = null;

        if(request.getParent_comment_id() != null) {
            parent = commentRepository.findById(Long.parseLong(request.getParent_comment_id()))
                    .orElseThrow(CommentNotFoundException::new);
        }

        Comment comment = commentRepository.save(Comment.builder()
                .writer(user)
                .content(request.getContent())
                .work(work)
                .parentComment(parent)
                .build());
        return comment.getCommentId();
    }
}
