package com.dalent.api.domain.service;

import com.dalent.api.domain.user.dao.UserRepository;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.user.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RankService {

    private final UserRepository userRepository;

    public List<UserInfoResponseDto> getRanks(String category) {
        List<User> users;
        switch (category) {
            case "art": {
                users = userRepository.findDistinctTop10ByStarsNotNullOrderByArtStarCountsDesc();
                break;
            }
            case "programming": {
                users = userRepository.findDistinctTop10ByStarsNotNullOrderByProgrammingStarCountsDesc();
                break;
            }
            case "fashion": {
                users = userRepository.findDistinctTop10ByStarsNotNullOrderByFashionStarCountsDesc();
                break;
            }
            case "music": {
                users = userRepository.findDistinctTop10ByStarsNotNullOrderByMusicStarCountsDesc();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return users.stream()
                .map(user -> {
                    Long gallery = (user.getGallery() != null) ? user.getGallery().getGalleryId() : null;
                    int stars = user.getStars(category);

                    return UserInfoResponseDto.builder()
                            .id(user.getId())
                            .nickname(user.getNickname())
                            .introduce(user.getIntroduce())
                            .profile_image(user.getProfile_image())
                            .gallery(gallery)
                            .stars(stars)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
