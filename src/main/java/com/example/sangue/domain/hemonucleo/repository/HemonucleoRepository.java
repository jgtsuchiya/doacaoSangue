package com.example.sangue.domain.hemonucleo.repository;

import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HemonucleoRepository extends JpaRepository<HemonucleoEntity, Long> {
    Page<HemonucleoEntity> findAllByAtivoTrue(Pageable pageable);

    Optional<HemonucleoEntity> findByIdAndAtivoTrue(Long id);

    boolean existsByUsuarioAdmId(Long usuarioAdmId);
}
