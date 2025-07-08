package com.example.sangue.domain.hemonucleo.service;

import com.example.sangue.domain.address.dto.AddressDTO;
import com.example.sangue.domain.address.entity.AddressEntity;
import com.example.sangue.domain.exception.ResourceNotFoundException;
import com.example.sangue.domain.hemonucleo.dto.HemonucleoDTO;
import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import com.example.sangue.domain.hemonucleo.repository.HemonucleoRepository;
import com.example.sangue.domain.user.entity.UserEntity;
import com.example.sangue.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HemonucleoService {

    @Autowired
    private HemonucleoRepository hemonucleoRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<HemonucleoDTO> findAllPaged(Pageable pageable) {
        Page<HemonucleoEntity> page = hemonucleoRepository.findAllByAtivoTrue(pageable);
        return page.map(this::toDTO);
    }

    @Transactional(readOnly = true)
    public HemonucleoDTO findById(Long id) {
        HemonucleoEntity entity = hemonucleoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hemonúcleo não encontrado com o id: " + id));
        return toDTO(entity);
    }

    @Transactional
    public HemonucleoDTO create(Long idUsuarioAdm, HemonucleoDTO dto) {
        UserEntity adminUser = userRepository.findById(idUsuarioAdm)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário ADM não encontrado com o id: " + idUsuarioAdm));

        if (hemonucleoRepository.existsByUsuarioAdmId(adminUser.getId())) {
            throw new IllegalStateException("Este usuário ADM já gerencia um hemonúcleo.");
        }

        HemonucleoEntity entity = new HemonucleoEntity();
        fromDTO(entity, dto);
        entity.setUsuarioAdm(adminUser);
        entity = hemonucleoRepository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    public HemonucleoDTO update(Long id, HemonucleoDTO dto) {
        HemonucleoEntity entity = hemonucleoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hemonúcleo não encontrado com o id: " + id));
        fromDTO(entity, dto);
        entity = hemonucleoRepository.save(entity);
        return toDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        HemonucleoEntity entity = hemonucleoRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hemonúcleo não encontrado com o id: " + id));
        entity.setAtivo(false);
        hemonucleoRepository.save(entity);
    }

    private HemonucleoDTO toDTO(HemonucleoEntity entity) {
        AddressDTO addressDTO = new AddressDTO(
                entity.getEndereco().getCep(),
                entity.getEndereco().getLogradouro(),
                entity.getEndereco().getNumero(),
                entity.getEndereco().getComplemento(),
                entity.getEndereco().getBairro(),
                entity.getEndereco().getCidade(),
                entity.getEndereco().getEstado()
        );
        return new HemonucleoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCnpj(),
                entity.getEmail(),
                entity.getTelefone(),
                addressDTO
        );
    }

    private void fromDTO(HemonucleoEntity entity, HemonucleoDTO dto) {
        entity.setNome(dto.nome());
        entity.setCnpj(dto.cnpj());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());

        if (entity.getEndereco() == null) {
            entity.setEndereco(new AddressEntity());
        }
        AddressEntity address = entity.getEndereco();
        address.setCep(dto.endereco().cep());
        address.setLogradouro(dto.endereco().logradouro());
        address.setNumero(dto.endereco().numero());
        address.setComplemento(dto.endereco().complemento());
        address.setBairro(dto.endereco().bairro());
        address.setCidade(dto.endereco().cidade());
        address.setEstado(dto.endereco().estado());
    }
}
