package com.security.mfa.jpaRepo;

import com.security.mfa.model.UserMfa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoForJpa extends JpaRepository<UserMfa,Long> {
    Optional<UserMfa>  findByUserName(String username);
}
