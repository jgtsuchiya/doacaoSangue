# Modelo Relacional

## Usuario

### usuario
    cpf (PK), 
    nome, 
    email, 
    senha, 
    telefone, 
    tipo_sanguineo, 
    sexo, 
    data_nascimento, 
    id_endereco (FK), 
    type, 
    user_role, 
    ativo

### hemonucleo
    cnpj (PK), 
    id_usuario (FK), 
    id_endereco (FK), 
    nome, 
    email, 
    senha, 
    telefone, 
    ativo

### endereco
    id (PK), 
    cep, 
    logradouro, 
    numero, 
    complemento, 
    bairro, 
    cidade, 
    estado

### doacao
    id_doador (PK), 
    data_doacao, 
    id_hemonucleo (FK)

### campanha
    id (PK), 
    nome, 
    descricao, 
    data_inicio, 
    data_termino, 
    meta_doacoes, 
    tipo_sanguineo_alvo, 
    id_hemonucleo (FK)