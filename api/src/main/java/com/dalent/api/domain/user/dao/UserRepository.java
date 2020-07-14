package com.dalent.api.domain.user.dao;

import com.dalent.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByNickname(String nickname);
    List<User> findDistinctTop10ByStarsNotNullOrderByArtStarCountsDesc();
    List<User> findDistinctTop10ByStarsNotNullOrderByMusicStarCountsDesc();
    List<User> findDistinctTop10ByStarsNotNullOrderByFashionStarCountsDesc();
    List<User> findDistinctTop10ByStarsNotNullOrderByProgrammingStarCountsDesc();
}
