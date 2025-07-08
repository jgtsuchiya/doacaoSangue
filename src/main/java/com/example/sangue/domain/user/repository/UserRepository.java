package com.example.sangue.domain.user.repository;

import com.example.sangue.domain.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findAllByAtivoTrue(Pageable pageable);

    Optional<UserEntity> findByIdAndAtivoTrue(Long id);

    Optional<UserEntity> findByEmail(String email);
}
