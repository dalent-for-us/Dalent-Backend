package com.dalent.api.domain.user.dao;

import com.dalent.api.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
