package com.dalent.api.domain.star.dao;

import com.dalent.api.domain.star.domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarRepository extends JpaRepository<Star, Long> {
}
