package com.example.usermanager.repository;

import com.example.usermanager.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

