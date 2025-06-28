package com.garliccastle.demo.domain.user.repository;

import com.garliccastle.demo.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
