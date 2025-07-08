package com.example.sangue.domain.user.service;

import com.example.sangue.domain.address.dto.AddressDTO;
import com.example.sangue.domain.address.entity.AddressEntity;
import com.example.sangue.domain.exception.ResourceNotFoundException;
import com.example.sangue.domain.user.dto.UserCreateDTO;
import com.example.sangue.domain.user.dto.UserResponseDTO;
import com.example.sangue.domain.user.dto.UserUpdateDTO;
import com.example.sangue.domain.user.entity.UserEntity;
import com.example.sangue.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAllPaged(Pageable pageable) {
        return userRepository.findAllByAtivoTrue(pageable).map(this::toResponseDTO);
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO dto) {
        UserEntity entity = new UserEntity();
        fromCreateDTO(entity, dto);
        entity = userRepository.save(entity);
        return toResponseDTO(entity);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserUpdateDTO dto) {
        UserEntity entity = userRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
        fromUpdateDTO(entity, dto);
        entity = userRepository.save(entity);
        return toResponseDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        UserEntity entity = userRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
        entity.setAtivo(false);
        userRepository.save(entity);
    }

    private UserResponseDTO toResponseDTO(UserEntity entity) {
        AddressDTO addressDTO = null;
        if (entity.getEndereco() != null) {
            AddressEntity address = entity.getEndereco();
            addressDTO = new AddressDTO(
                    address.getCep(), address.getLogradouro(), address.getNumero(),
                    address.getComplemento(), address.getBairro(), address.getCidade(), address.getEstado()
            );
        }

        return new UserResponseDTO(
                entity.getId(), entity.getNome(), entity.getCpf(), entity.getEmail(),
                entity.getTelefone(), entity.getTipoSanguineo(), entity.getDataNascimento(),
                entity.getAtivo(), addressDTO, entity.getUserRole()
        );
    }

    private void fromCreateDTO(UserEntity entity, UserCreateDTO dto) {
        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());
        entity.setSenha(dto.senha());
        entity.setTelefone(dto.telefone());
        entity.setTipoSanguineo(dto.tipoSanguineo());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setUserRole(dto.userRole());

        if (dto.endereco() != null) {
            AddressEntity address = new AddressEntity();
            address.setCep(dto.endereco().cep());
            address.setLogradouro(dto.endereco().logradouro());
            address.setNumero(dto.endereco().numero());
            address.setComplemento(dto.endereco().complemento());
            address.setBairro(dto.endereco().bairro());
            address.setCidade(dto.endereco().cidade());
            address.setEstado(dto.endereco().estado());
            entity.setEndereco(address);
        }
    }

    private void fromUpdateDTO(UserEntity entity, UserUpdateDTO dto) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setTelefone(dto.telefone());

        if (dto.endereco() != null) {
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
}
