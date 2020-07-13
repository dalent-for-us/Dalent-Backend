package com.dalent.api.domain.follow.dao;

import com.dalent.api.domain.follow.domain.Follow;
import com.dalent.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByUser(User user);
    List<Follow> findAllByTarget(User user);
    Optional<Follow> findByUserAndTarget(User user, User target);
}
