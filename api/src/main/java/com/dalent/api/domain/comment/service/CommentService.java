package com.dalent.api.domain.comment.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.comment.dao.CommentRepository;
import com.dalent.api.domain.comment.domain.Comment;
import com.dalent.api.domain.comment.dto.CommentResponseDto;
import com.dalent.api.domain.comment.dto.CreateCommentRequestDto;
import com.dalent.api.domain.comment.exception.CommentNotFoundException;
import com.dalent.api.domain.comment.exception.NotMyCommentException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.dao.WorkRepository;
import com.dalent.api.domain.work.domain.Work;
import com.dalent.api.domain.work.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CommentResponseDto> getComments(String workId) {
        Work work = workRepository.findById(Long.parseLong(workId)).orElseThrow(WorkNotFoundException::new);
        return work.getComments().stream()
                .map(comment -> {
                    List<Long> comments = comment.getChildComment().stream()
                            .map(Comment::getCommentId)
                            .collect(Collectors.toList());

                    return CommentResponseDto.builder()
                            .comment_id(comment.getCommentId())
                            .content(comment.getContent())
                            .work_id(comment.getWork().getWorkId())
                            .child_comments(comments)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void reviseComment(String commentId, String content) {
        Comment comment = commentRepository.findById(Long.parseLong(commentId))
                .orElseThrow(CommentNotFoundException::new);

        checkOwner(comment);

        comment.reviseContent(content);
        commentRepository.save(comment);
    }

    public void removeComment(String commentId) {
        Comment comment = commentRepository.findById(Long.parseLong(commentId))
                .orElseThrow(CommentNotFoundException::new);

        checkOwner(comment);

        commentRepository.delete(comment);
    }

    private void checkOwner(Comment comment) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);

        if(!user.equals(comment.getWriter())) throw new NotMyCommentException();
    }
}
