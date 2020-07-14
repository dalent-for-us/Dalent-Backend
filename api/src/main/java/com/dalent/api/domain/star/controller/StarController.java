package com.dalent.api.domain.star.controller;

import com.dalent.api.domain.star.service.StarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
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

    @GetMapping("/{work_id}")
    public List<String> getStars(@PathVariable("work_id") String workId) {
        return starService.getStars(workId);
    }

    @DeleteMapping("/{work_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Transactional
    public void cancelStar(@PathVariable("work_id") String workId) {
        starService.cancelStar(workId);
    }
}
