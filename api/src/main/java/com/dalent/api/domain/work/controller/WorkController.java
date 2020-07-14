package com.dalent.api.domain.work.controller;

import com.dalent.api.domain.work.dto.CreateWorkRequestDto;
import com.dalent.api.domain.work.dto.WorkDetailResponseDto;
import com.dalent.api.domain.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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

    @GetMapping
    public List<WorkDetailResponseDto> getWorks(@RequestParam String category) {
        return workService.getWorks(category, null);
    }

    @GetMapping("/users/{nickname}")
    public List<WorkDetailResponseDto> getUserWorks(@PathVariable("nickname") String userId,
                                                    @RequestParam String category) {
        return workService.getWorks(category, userId);
    }

    @PatchMapping("/{work_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void updateWork(@PathVariable("work_id") String workId,
                           @RequestBody CreateWorkRequestDto requestDto) {
        workService.updateWork(Long.parseLong(workId), requestDto);
    }

    @DeleteMapping("/{work_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteWork(@PathVariable("work_id") String workId) {
        workService.deleteWork(Long.parseLong(workId));
    }
}
