package com.example.sangue.domain.donation.service;

import com.example.sangue.domain.campaign.repository.CampaignRepository;
import com.example.sangue.domain.donation.dto.DonationCreateDTO;
import com.example.sangue.domain.donation.dto.DonationResponseDTO;
import com.example.sangue.domain.donation.entity.DonationEntity;
import com.example.sangue.domain.donation.repository.DonationRepository;
import com.example.sangue.domain.exception.ResourceNotFoundException;
import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import com.example.sangue.domain.hemonucleo.repository.HemonucleoRepository;
import com.example.sangue.domain.user.entity.UserEntity;
import com.example.sangue.domain.user.enums.UserRole;
import com.example.sangue.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private HemonucleoRepository hemonucleoRepository;

    @Transactional
    public DonationResponseDTO registrar(DonationCreateDTO dto) {
        UserEntity doador = userRepository.findByIdAndAtivoTrue(dto.idDoador())
                .orElseThrow(() -> new ResourceNotFoundException("Doador ativo não encontrado com o id: " + dto.idDoador()));

        HemonucleoEntity hemonucleo = hemonucleoRepository.findByIdAndAtivoTrue(dto.idHemonucleo())
                .orElseThrow(() -> new ResourceNotFoundException("Hemonúcleo não encontrado com o id: " + dto.idHemonucleo()));

        DonationEntity entity = new DonationEntity();
        entity.setDoador(doador);
        entity.setHemonucleo(hemonucleo);
        entity.setDataDoacao(LocalDateTime.now());

        entity = donationRepository.save(entity);
        return toResponseDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<DonationResponseDTO> listar(Pageable pageable, Long idCampanha, Long idDoador) {
        Page<DonationEntity> page;

        if (idDoador != null) {
            page = donationRepository.findByDoadorId(idDoador, pageable);
        } else {
            page = donationRepository.findAll(pageable);
        }

        return page.map(this::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public DonationResponseDTO buscarPorId(Long id) {
        DonationEntity entity = donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doação não encontrada com o id: " + id));
        return toResponseDTO(entity);
    }

    private DonationResponseDTO toResponseDTO(DonationEntity entity) {
        return new DonationResponseDTO(
                entity.getId(),
                entity.getDataDoacao(),
                entity.getDoador().getId(),
                entity.getDoador().getNome(),
                entity.getDoador().getCpf(),
                entity.getHemonucleo().getId(),
                entity.getHemonucleo().getNome()
        );
    }
}

