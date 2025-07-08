package com.example.sangue.domain.campaign.entity;

import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
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

import java.time.LocalDate;

@Entity
@Table(name = "campanha")
@Getter
@Setter
public class CampaignEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataTermino;

    private Integer metaDoacoes;

    private String tipoSanguineoAlvo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hemonucleo", nullable = false)
    private HemonucleoEntity hemonucleo;
}
