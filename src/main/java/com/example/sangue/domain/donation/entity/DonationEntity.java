package com.example.sangue.domain.donation.entity;

import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import com.example.sangue.domain.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "doacao")
@Getter
@Setter
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_doacao", nullable = false)
    private LocalDateTime dataDoacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doador", nullable = false)
    private UserEntity doador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hemonucleo", nullable = false)
    private HemonucleoEntity hemonucleo;
}
