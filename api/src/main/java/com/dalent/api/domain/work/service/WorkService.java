package com.dalent.api.domain.work.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.dao.WorkRepository;
import com.dalent.api.domain.work.domain.Category;
import com.dalent.api.domain.work.domain.MediaType;
import com.dalent.api.domain.work.domain.Work;
import com.dalent.api.domain.work.dto.CreateWorkRequestDto;
import com.dalent.api.domain.work.dto.WorkDetailResponseDto;
import com.dalent.api.domain.work.exception.NotMyWorkException;
import com.dalent.api.domain.work.exception.WorkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public WorkDetailResponseDto findWork(Long workId) {
        Work work = workRepository.findById(workId).orElseThrow(WorkNotFoundException::new);

        return WorkDetailResponseDto.builder()
                .work_id(work.getWorkId())
                .category(work.getCategory().getValue())
                .content(work.getContent())
                .media_link(work.getMediaLink())
                .media_type(work.getMediaType().getValue())
                .nickname(work.getAuthor().getNickname())
                .thumbnail_image(work.getThumbnailImage())
                .title(work.getTitle())
                .stars(work.getStars())
                .build();
    }

    public List<WorkDetailResponseDto> getWorks(String category) {
        List<Work> works = workRepository.findByCategoryOrderByStarsDesc(Category.valueOf(category.toUpperCase()));
        return works.stream()
                .map(work -> WorkDetailResponseDto.builder()
                        .thumbnail_image(work.getThumbnailImage())
                        .title(work.getTitle())
                        .nickname(work.getAuthor().getNickname())
                        .media_type(work.getMediaType().getValue())
                        .media_link(work.getMediaLink())
                        .content(work.getContent())
                        .category(work.getCategory().getValue())
                        .work_id(work.getWorkId())
                        .stars(work.getStars())
                        .build())
                .collect(Collectors.toList());
    }

    public void updateWork(Long workId, CreateWorkRequestDto request) {
        Work work = workRepository.findById(workId).orElseThrow(WorkNotFoundException::new);
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!work.getAuthor().getNickname().equals(nickname)) throw new NotMyWorkException();

        work.updateWork(request.getCategory(), request.getTitle(), request.getContent(),
                request.getMedia_type(), request.getMedia_link(), request.getThumbnail_link());
        workRepository.save(work);
    }


    public void deleteWork(Long workId) {
        Work work = workRepository.findById(workId).orElseThrow(WorkNotFoundException::new);
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!work.getAuthor().getNickname().equals(nickname)) throw new NotMyWorkException();

        workRepository.delete(work);
    }
}
