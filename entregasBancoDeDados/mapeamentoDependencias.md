# Dependências Funcionais

---

## USUARIO

**Chave primária:** `cpf`

- `cpf → nome, email, senha, telefone, tipo_sanguineo, sexo, data_nascimento, id_endereco, type, user_role, ativo`  


- `id_endereco → cep, logradouro, numero, complemento, bairro, cidade, estado`  
  (dependência transitiva de `ENDERECO`)

---

## HEMONUCLEO

**Chave primária:** `cnpj`

- `cnpj → nome, email, senha, telefone, cpf_usuario, id_endereco, ativo`  


- `cpf_usuario → nome, email, senha, telefone, tipo_sanguineo, sexo, data_nascimento, id_endereco, type, user_role, ativo`  
  (dependência transitiva de `USUARIO.cpf`)


- `id_endereco → cep, logradouro, numero, complemento, bairro, cidade, estado`  
  (dependência transitiva de `ENDERECO.id`)

---

## ENDERECO

**Chave primária:** `id`

- `id → cep, logradouro, numero, complemento, bairro, cidade, estado`  

---

## CAMPANHA

**Chave primária:** `id`

- `id → nome, descricao, data_inicio, data_termino, meta_doacoes, tipo_sanguineo_alvo, cnpj_hemonucleo`


- `cnpj_hemonucleo → nome, email, senha, telefone, cpf_usuario, id_endereco, ativo`  
  (dependência transitiva de `HEMONUCLEO.cnpj`)

---

## DOACAO

**Chave primária (composta):** `cpf_doador, data_doacao`

- `cpf_doador, data_doacao → cnpj_hemonucleo`  
  (A combinação identifica a ocorrência específica da doação)


- `cpf_doador → nome, email, senha, telefone, tipo_sanguineo, sexo, data_nascimento, id_endereco, type, user_role, ativo`  
  (dependência transitiva de `USUARIO.cpf`)


- `cnpj_hemonucleo → nome, email, senha, telefone, cpf_usuario, id_endereco, ativo`  
  (dependência transitiva de `HEMONUCLEO.cnpj`.)