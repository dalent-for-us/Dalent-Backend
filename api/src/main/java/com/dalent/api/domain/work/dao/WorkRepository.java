package com.dalent.api.domain.work.dao;

import com.dalent.api.domain.user.domain.User;
import com.dalent.api.domain.work.domain.Category;
import com.dalent.api.domain.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findByCategoryOrderByStarsDesc(Category category);
    List<Work> findByCategoryAndAuthorOrderByStarsDesc(Category category, User user);
}