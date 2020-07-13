package com.dalent.api.domain.comment.controller;

import com.dalent.api.domain.comment.dto.CommentResponseDto;
import com.dalent.api.domain.comment.dto.CreateCommentRequestDto;
import com.dalent.api.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public long createComment(@RequestBody CreateCommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    @GetMapping
    public List<CommentResponseDto> getComments(@RequestParam String work_id) {
        return commentService.getComments(work_id);
    }

    @PatchMapping("/{comment_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void reviseComment(@PathVariable("comment_id") String commentId,
                              @RequestBody Map<String, String> content) {

        commentService.reviseComment(commentId, content.get("content"));
    }

    @DeleteMapping("/{comment_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable("comment_id") String commentId) {
        commentService.removeComment(commentId);
    }
}
