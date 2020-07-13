package com.dalent.api.domain.star.controller;

import com.dalent.api.domain.star.dto.GiveStarRequestDto;
import com.dalent.api.domain.star.service.StarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/stars")
@RestController
public class StarController {

    private final StarService starService;

    @PostMapping
    public long giveStar(@RequestBody Map<String, String> work_id) {
        return starService.giveStar(work_id.get("work_id"));
    }
}
