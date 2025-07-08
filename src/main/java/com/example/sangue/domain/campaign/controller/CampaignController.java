package com.example.sangue.domain.campaign.controller;

import com.example.sangue.domain.campaign.controller.openApi.CampaignControllerOpenApi;
import com.example.sangue.domain.campaign.dto.CampaignDTO;
import com.example.sangue.domain.campaign.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/campanhas")
public class CampaignController implements CampaignControllerOpenApi {
    @Autowired
    private CampaignService campaignService;

    @GetMapping
    public ResponseEntity<Page<CampaignDTO>> findAll(Pageable pageable) {
        Page<CampaignDTO> list = campaignService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CampaignDTO> findById(@PathVariable Long id) {
        CampaignDTO dto = campaignService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CampaignDTO> create(@RequestBody CampaignDTO dto) {
        dto = campaignService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CampaignDTO> update(@PathVariable Long id, @RequestBody CampaignDTO dto) {
        dto = campaignService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
