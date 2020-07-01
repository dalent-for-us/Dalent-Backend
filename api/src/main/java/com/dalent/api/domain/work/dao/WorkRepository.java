package com.dalent.api.domain.work.dao;

import com.dalent.api.domain.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {

}