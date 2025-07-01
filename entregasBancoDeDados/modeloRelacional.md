# Modelo Relacional

---

## USUARIO


- `cpf, nome, email, senha, telefone, tipo_sanguineo, sexo, data_nascimento, id_endereco, type, user_role, ativo`

---

## HEMONUCLEO

- `cnpj, nome, email, senha, telefone, cpf_usuario, id_endereco, ativo`

---

## ENDERECO

- `id, cep, logradouro, numero, complemento, bairro, cidade, estado`

---

## CAMPANHA

- `id, nome, descricao, data_inicio, data_termino, meta_doacoes, tipo_sanguineo_alvo, cnpj_hemonucleo`

---

## DOACAO

- `cpf_doador, data_doacao, cnpj_hemonucleo`