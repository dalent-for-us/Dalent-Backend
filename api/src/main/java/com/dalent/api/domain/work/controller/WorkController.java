package com.dalent.api.domain.work.controller;

import com.dalent.api.domain.work.dto.CreateWorkRequestDto;
import com.dalent.api.domain.work.dto.WorkDetailResponseDto;
import com.dalent.api.domain.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/works")
@RestController
public class WorkController {

    private final WorkService workService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long createWork(@RequestBody CreateWorkRequestDto requestDto) {
        return workService.createWork(requestDto);
    }

    @GetMapping("/{work_id}")
    public WorkDetailResponseDto findWork(@PathVariable("work_id") String workId) {
        return workService.findWork(Long.parseLong(workId));
    }
}
