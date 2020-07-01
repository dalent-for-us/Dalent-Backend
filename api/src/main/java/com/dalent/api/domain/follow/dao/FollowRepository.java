package com.dalent.api.domain.follow.dao;

import com.dalent.api.domain.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
