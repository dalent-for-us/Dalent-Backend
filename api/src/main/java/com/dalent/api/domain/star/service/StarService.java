package com.dalent.api.domain.star.service;

import com.dalent.api.domain.auth.exception.UserNotFoundException;
import com.dalent.api.domain.star.dao.StarRepository;
import com.dalent.api.domain.star.domain.Star;
import com.dalent.api.domain.star.exception.AlreadyGaveStarException;
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

@RequiredArgsConstructor
@Service
public class StarService {

    private final StarRepository starRepository;

    private final UserRepository userRepository;

    private final WorkRepository workRepository;

    public long giveStar(String workId) {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);

        Work work = workRepository.findById(Long.parseLong(workId))
                .orElseThrow(WorkNotFoundException::new);

        User target = userRepository.findByNickname(work.getAuthor().getNickname())
                .orElseThrow(UserNotFoundException::new);

        if(starRepository.findByUser(user).isPresent()) throw new AlreadyGaveStarException();

        Star star = starRepository.save(Star.builder()
                .category(work.getCategory())
                .user(user)
                .target(target)
                .work(work)
                .build());
        return star.getStarId();
    }

    public List<String> getStars(String workId) {
        Work work = workRepository.findById(Long.parseLong(workId))
                .orElseThrow(WorkNotFoundException::new);

        List<Star> stars = starRepository.findAllByWork(work);
        return stars.stream()
                .map(star -> {
                  return star.getUser().getNickname();
                })
                .collect(Collectors.toList());
    }
}