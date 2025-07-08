package com.example.sangue.domain.donation.repository;

import com.example.sangue.domain.donation.entity.DonationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {
    Page<DonationEntity> findByDoadorId(Long doadorId, Pageable pageable);
}
