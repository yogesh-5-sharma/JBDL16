package com.example.QAapp.repository;

import com.example.QAapp.models.Author;
import com.example.QAapp.security.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    void deleteBySystemUser(SystemUser systemUser);

    Optional<Author> findBySystemUser(SystemUser systemUser);
}
