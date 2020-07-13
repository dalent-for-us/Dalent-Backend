package com.dalent.api.domain.comment.controller;

import com.dalent.api.domain.comment.dto.CommentResponseDto;
import com.dalent.api.domain.comment.dto.CreateCommentRequestDto;
import com.dalent.api.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
