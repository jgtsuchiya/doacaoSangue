# Levantamento de Requisitos

## Tema: Sistema de Gerenciamento de Doações

### 1. Definição do tema e justificativa
O tema escolhido é o desenvolvimento de um sistema de gerenciamento de doações. Esse sistema tem como objetivo organizar e facilitar o processo de doações entre doadores e hemonúcleos. A relevância do tema está no seu impacto social, podendo aumentar a transparência, agilidade e eficiência das doações, garantindo que os recursos cheguem às campanhas corretas e beneficiem quem realmente precisa. Além disso, pessoalmente tenho excelente histórico de doação e atendimento do hemonúcleo de Campo Mourão. Desde que vim para a cidade e ingressei na universidade, tenho feito doações periódicas e sempre retorno devido ao ótimo atendimento.

### 2. Atores envolvidos
- **Doador**: Pessoa física ou jurídica que realiza doações para campanhas.
- **Hemonúcleo**: Instituição responsável por criar campanhas e administrar os recursos recebidos.
- **Administrador do Sistema**: Responsável pelo gerenciamento geral da plataforma, controle de cadastros, verificação de campanhas, etc.
- **Beneficiário**: Pessoa que receberá a doação.

### 3. Entidades principais

#### Doadores
- ID do doador
- Nome
- CPF
- E-mail
- Senha
- Telefone
- Endereço
- Tipo sanguíneo
- Sexo
- Idade
- Grupo

#### Hemonúcleo
- ID da organização
- Nome
- CNPJ
- E-mail
- Senha
- Telefone
- Endereço

#### Campanhas
- ID da campanha
- Nome da campanha
- Descrição
- Data de início
- Data de término
- Meta de doações
- ID da organização (FK)
- Tipo sanguíneo alvo

#### Doações (Transações)
- ID da doação
- ID do doador (FK)
- ID da campanha (FK)
- Data da doação

#### Administrador
- ID do administrador
- Nome
- E-mail
- Senha (criptografada)

#### Beneficiário
- ID do beneficiário
- Nome
- CPF
- E-mail
- Senha
- Telefone
- Endereço
- Tipo sanguíneo
- Sexo
- Idade
- Grupo

### 4. Sugestão inicial de relacionamentos

#### 4.1. Doadores ⇨ Doações
- **Relacionamento**: Um doador pode realizar várias doações, mas cada doação está associada a apenas um doador.
- **Tipo**: 1:N (um para muitos)

#### 4.2. Campanhas ⇨ Doações
- **Relacionamento**: Uma campanha pode receber várias doações, mas cada doação pertence a apenas uma campanha.
- **Tipo**: 1:N

#### 4.3. Hemonúcleo ⇨ Campanhas
- **Relacionamento**: Um hemonúcleo pode criar e administrar várias campanhas, mas cada campanha é gerida por um único hemonúcleo.
- **Tipo**: 1:N

#### 4.4. Administrador ⇨ Entidades do sistema (função lógica)
- O administrador realiza operações administrativas (como criar, editar, remover registros de hemonúcleos, campanhas, doadores, etc.).
- Não se estabelece relacionamento direto no modelo físico, pois é uma função de controle de acesso no sistema.

#### 4.5. Doações ⇨ Beneficiários (opcional)
- Se o sistema for projetado para rastrear exatamente quem recebeu o sangue de cada doação, pode-se criar um relacionamento:
    - 1:N entre Doações e Beneficiários (ou N:N, dependendo da lógica da distribuição).
- Na maioria dos sistemas, o sangue doado entra no estoque geral, e os beneficiários são atendidos conforme necessidade.

### 5. Requisitos funcionais
O sistema deverá ser capaz de:
- Permitir o cadastro, login e atualização de doadores e hemonúcleos.
- Permitir que hemonúcleos criem, editem e removam campanhas.
- Permitir que doadores visualizem campanhas disponíveis e realizem doações.
- Registrar todas as doações com informações detalhadas (tipo sanguíneo, quantidade, doador).
- Permitir que doadores e hemonúcleos consultem o histórico de doações.
- Fornecer um painel administrativo para o controle das entidades cadastradas.
- Exibir estatísticas básicas, como quantidade total de sangue por campanha ou quantidade de doadores por campanha.
- Validar CNPJ/CPF no momento do cadastro.
- Garantir a segurança dos dados, especialmente das informações pessoais e financeiras.
