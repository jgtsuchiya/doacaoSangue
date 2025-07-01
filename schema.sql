CREATE TABLE usuario (
    cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    tipo_sanguineo VARCHAR(3),
    sexo VARCHAR(20),
    data_nascimento DATE,
    id_endereco BIGINT,
    type VARCHAR(20) NOT NULL,
    user_role VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES endereco(id)
);

CREATE TABLE endereco (
    id BIGSERIAL PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

CREATE TABLE hemonucleo (
    cnpj VARCHAR(18) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    id_usuario BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    FOREIGN KEY (id_endereco) REFERENCES endereco(id)
);

CREATE TABLE campanha (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_termino DATE NOT NULL,
    meta_doacoes INT,
    tipo_sanguineo_alvo VARCHAR(3),
    id_hemonucleo BIGINT,
    FOREIGN KEY (id_hemonucleo) REFERENCES hemonucleo(id)
);

CREATE TABLE doacao (
    id_doador BIGINT PRIMARY KEY NOT NULL,
    data_doacao TIMESTAMP NOT NULL,
    id_hemonucleo BIGINT NOT NULL,
    FOREIGN KEY (id_doador) REFERENCES usuario(id),
    FOREIGN KEY (id_hemonucleo) REFERENCES hemonucleo(id)
);