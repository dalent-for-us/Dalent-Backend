package com.dalent.api.domain.work.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.dao.WorkRepository;
import com.dalent.api.domain.work.domain.Category;
import com.dalent.api.domain.work.domain.MediaType;
import com.dalent.api.domain.work.domain.Work;
import com.dalent.api.domain.work.dto.CreateWorkRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkService {

    private final WorkRepository workRepository;

    private final UserRepository userRepository;

    public long createWork(CreateWorkRequestDto request) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
        Category category = Category.valueOf(request.getCategory().toUpperCase());
        MediaType mediaType = MediaType.valueOf(request.getMedia_type().toUpperCase());

        Work work = workRepository.save(Work.builder()
                .author(user)
                .category(category)
                .content(request.getContent())
                .mediaLink(request.getMedia_link())
                .mediaType(mediaType)
                .thumbnailImage(request.getThumbnail_link())
                .title(request.getTitle())
                .build());

        return work.getWorkId();
    }
}