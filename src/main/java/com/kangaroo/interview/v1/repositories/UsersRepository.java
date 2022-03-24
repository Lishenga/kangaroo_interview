package com.kangaroo.interview.v1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.kangaroo.interview.v1.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional<Users> findByEmail(String email);

    Page<Users> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}
