package com.example.sangue.configuration;

import com.example.sangue.domain.address.entity.AddressEntity;
import com.example.sangue.domain.donation.dto.DonationCreateDTO;
import com.example.sangue.domain.donation.service.DonationService;
import com.example.sangue.domain.hemonucleo.entity.HemonucleoEntity;
import com.example.sangue.domain.hemonucleo.repository.HemonucleoRepository;
import com.example.sangue.domain.user.entity.UserEntity;
import com.example.sangue.domain.user.enums.UserRole;
import com.example.sangue.domain.user.enums.UserType;
import com.example.sangue.domain.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final HemonucleoRepository hemonucleoRepository;
    private final DonationService donationService;

    @PostConstruct
    public void init() {
        if (userRepository.count() > 0 || hemonucleoRepository.count() > 0) return;

        AddressEntity address = new AddressEntity();
        address.setCep("87300000");
        address.setLogradouro("Rua Principal");
        address.setNumero("123");
        address.setComplemento("Casa");
        address.setBairro("Centro");
        address.setCidade("Campo Mourão");
        address.setEstado("PR");

        UserEntity adm = new UserEntity();
        adm.setNome("Admin Teste");
        adm.setCpf("00000000000");
        adm.setEmail("teste@admin.com");
        adm.setSenha("123456");
        adm.setTipoSanguineo("O+");
        adm.setUserRole(UserRole.ADM);
        adm.setType(UserType.DOADOR);
        adm.setDataNascimento(LocalDate.of(1990, 1, 1));
        adm.setTelefone("44999999999");
        adm.setAtivo(true);
        adm.setEndereco(cloneAddress(address));
        userRepository.save(adm);

        UserEntity user1 = createClient("João da Silva", "11111111111", "joao@email.com", "A+", address);
        UserEntity user2 = createClient("Maria Souza", "22222222222", "maria@email.com", "B+", address);
        UserEntity user3 = createClient("Carlos Lima", "33333333333", "carlos@email.com", "O-", address);
        userRepository.saveAll(List.of(user1, user2, user3));

        HemonucleoEntity hemonucleo = new HemonucleoEntity();
        hemonucleo.setNome("Hemonúcleo Campo Mourão");
        hemonucleo.setCnpj("12345678000100");
        hemonucleo.setEmail("contato@hemo.com");
        hemonucleo.setTelefone("4433334444");
        hemonucleo.setEndereco(cloneAddress(address));
        hemonucleo.setUsuarioAdm(adm);
        hemonucleo.setAtivo(true);
        hemonucleoRepository.save(hemonucleo);

        donationService.registrar(new DonationCreateDTO(user1.getId(), hemonucleo.getId()));
        donationService.registrar(new DonationCreateDTO(user2.getId(), hemonucleo.getId()));
        donationService.registrar(new DonationCreateDTO(user3.getId(), hemonucleo.getId()));
        donationService.registrar(new DonationCreateDTO(user1.getId(), hemonucleo.getId()));
        donationService.registrar(new DonationCreateDTO(user2.getId(), hemonucleo.getId()));
    }

    private UserEntity createClient(String nome, String cpf, String email, String tipoSanguineo, AddressEntity baseAddress) {
        UserEntity user = new UserEntity();
        user.setNome(nome);
        user.setCpf(cpf);
        user.setEmail(email);
        user.setSenha("123456");
        user.setTipoSanguineo(tipoSanguineo);
        user.setUserRole(UserRole.CLIENT);
        user.setType(UserType.DOADOR);
        user.setDataNascimento(LocalDate.of(1995, 5, 15));
        user.setTelefone("44988887777");
        user.setAtivo(true);
        user.setEndereco(cloneAddress(baseAddress));
        return user;
    }

    private AddressEntity cloneAddress(AddressEntity original) {
        AddressEntity address = new AddressEntity();
        address.setCep(original.getCep());
        address.setLogradouro(original.getLogradouro());
        address.setNumero(original.getNumero());
        address.setComplemento(original.getComplemento());
        address.setBairro(original.getBairro());
        address.setCidade(original.getCidade());
        address.setEstado(original.getEstado());
        return address;
    }
}
