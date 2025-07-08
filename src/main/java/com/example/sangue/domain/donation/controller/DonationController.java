package com.example.sangue.domain.donation.controller;

import com.example.sangue.domain.donation.controller.openApi.DonationControllerOpenApi;
import com.example.sangue.domain.donation.dto.DonationCreateDTO;
import com.example.sangue.domain.donation.dto.DonationResponseDTO;
import com.example.sangue.domain.donation.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/doacoes")
public class DonationController implements DonationControllerOpenApi {
    @Autowired
    private DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationResponseDTO> registrar(@RequestBody DonationCreateDTO dto) {
        DonationResponseDTO responseDto = donationService.registrar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDto.id()).toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<DonationResponseDTO>> listar(
            Pageable pageable,
            @RequestParam(value = "campanhaId", required = false) Long campanhaId,
            @RequestParam(value = "doadorId", required = false) Long doadorId) {

        Page<DonationResponseDTO> list = donationService.listar(pageable, campanhaId, doadorId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DonationResponseDTO> buscarPorId(@PathVariable Long id) {
        DonationResponseDTO dto = donationService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }
}
