package com.example.sangue.domain.campaign.service;

import com.example.sangue.domain.campaign.dto.CampaignDTO;
import com.example.sangue.domain.campaign.entity.CampaignEntity;
import com.example.sangue.domain.campaign.repository.CampaignRepository;
import com.example.sangue.domain.exception.ResourceNotFoundException;
import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import com.example.sangue.domain.hemonucleo.repository.HemonucleoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private HemonucleoRepository hemonucleoRepository;

    @Transactional(readOnly = true)
    public Page<CampaignDTO> findAllPaged(Pageable pageable) {
        Page<CampaignEntity> page = campaignRepository.findAll(pageable);
        return page.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public CampaignDTO findById(Long id) {
        CampaignEntity entity = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com o id: " + id));
        return toDTO(entity);
    }

    @Transactional
    public CampaignDTO create(CampaignDTO dto) {
        if (dto.dataInicio().isAfter(dto.dataTermino())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de término.");
        }

        CampaignEntity entity = new CampaignEntity();
        fromDTO(entity, dto);
        entity = campaignRepository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    public CampaignDTO update(Long id, CampaignDTO dto) {
        if (dto.dataInicio().isAfter(dto.dataTermino())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de término.");
        }

        CampaignEntity entity = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha não encontrada com o id: " + id));
        fromDTO(entity, dto);
        entity = campaignRepository.save(entity);
        return toDTO(entity);
    }

    private CampaignDTO toDTO(CampaignEntity entity) {
        return new CampaignDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getDataInicio(),
                entity.getDataTermino(),
                entity.getMetaDoacoes(),
                entity.getTipoSanguineoAlvo(),
                entity.getHemonucleo().getId(),
                entity.getHemonucleo().getNome()
        );
    }

    private void fromDTO(CampaignEntity entity, CampaignDTO dto) {
        HemonucleoEntity hemonucleo = hemonucleoRepository.findByIdAndAtivoTrue(dto.idHemonucleo())
                .orElseThrow(() -> new ResourceNotFoundException("Hemonúcleo ativo não encontrado com o id: " + dto.idHemonucleo()));

        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
        entity.setDataInicio(dto.dataInicio());
        entity.setDataTermino(dto.dataTermino());
        entity.setMetaDoacoes(dto.metaDoacoes());
        entity.setTipoSanguineoAlvo(dto.tipoSanguineoAlvo());
        entity.setHemonucleo(hemonucleo);
    }
}
