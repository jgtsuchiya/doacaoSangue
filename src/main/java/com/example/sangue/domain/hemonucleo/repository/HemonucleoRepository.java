package com.example.sangue.domain.hemonucleo.repository;

import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HemonucleoRepository extends JpaRepository<HemonucleoEntity, Long> {
}
