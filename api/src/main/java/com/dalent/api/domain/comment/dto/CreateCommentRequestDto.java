package com.dalent.api.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequestDto {

    private String work_id;
    private String content;
    private String parent_comment_id;
}
