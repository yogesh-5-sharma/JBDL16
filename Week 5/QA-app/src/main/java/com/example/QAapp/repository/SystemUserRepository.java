package com.example.QAapp.repository;

import com.example.QAapp.security.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    Optional<SystemUser> findByUsername(String username);
}
