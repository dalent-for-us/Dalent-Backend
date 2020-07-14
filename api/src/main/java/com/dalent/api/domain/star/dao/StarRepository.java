package com.dalent.api.domain.star.dao;

import com.dalent.api.domain.star.domain.Star;
import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<Star, Long> {
    List<Star> findAllByWork(Work work);
    Optional<Star> findByUser(User user);
    Optional<Star> findByUserAndWork(User user, Work work);
}
