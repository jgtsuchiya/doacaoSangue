package com.example.sangue.domain.hemonucleo.controller;

import com.example.sangue.domain.hemonucleo.controller.openApi.HemonucleoControllerOpenApi;
import com.example.sangue.domain.hemonucleo.dto.HemonucleoDTO;
import com.example.sangue.domain.hemonucleo.service.HemonucleoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hemonucleos")
public class HemonucleoController implements HemonucleoControllerOpenApi {
    @Autowired
    private HemonucleoService hemonucleoService;

    @GetMapping
    public ResponseEntity<Page<HemonucleoDTO>> findAll(Pageable pageable) {
        Page<HemonucleoDTO> list = hemonucleoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HemonucleoDTO> findById(@PathVariable Long id) {
        HemonucleoDTO dto = hemonucleoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/hemonucleos")
    public ResponseEntity<HemonucleoDTO> createHemonucleo(
            @RequestParam Long idUsuarioAdm,
            @RequestBody HemonucleoDTO dto
    ) {
        HemonucleoDTO created = hemonucleoService.create(idUsuarioAdm, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HemonucleoDTO> update(@PathVariable Long id, @RequestBody HemonucleoDTO dto) {
        dto = hemonucleoService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hemonucleoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
