# Projeto: Cadastro de Alunos (CRUD)

## Descrição

Este é um projeto simples de cadastro de alunos implementado com **Java 17**, utilizando **JDBC** para acesso ao banco de dados **PostgreSQL**.  
O banco de dados é executado dentro de um container **Docker**, e a aplicação segue uma arquitetura em camadas para garantir organização, coesão e separação de responsabilidades.

---

## Tecnologias e Ferramentas

- Java 17
- JDBC (Java Database Connectivity)
- PostgreSQL (em container Docker)
- Docker / Docker Compose
- IDE (Eclipse)

---

## Arquitetura de Camadas

O projeto está organizado para manter a **separação de responsabilidades**:

- **`principal`**: Ponto de entrada do programa, invoca o menu da aplicação.
- **`controllers`**: Interpreta as opções do usuário e delega ações para o service.
- **`services`**: Contém a lógica de negócio e orquestra a comunicação entre controller e repositório.
- **`validators`**: Validação de regras de negócio e integridade de dados.
- **`views`**: Responsável por exibir informações ao usuário (via console).
- **`repositories`**: Acesso direto ao banco de dados com comandos SQL (CRUD).
- **`factories`**: Contém a interface `ConnectionFactory`, que fornece conexões com o banco PostgreSQL.
- **`exceptions`**: Exceções customizadas para tratar erros específicos de negócio ou de persistência.
- **`scripts`**: Scripts SQL para criação da tabela `aluno`.

---

## Como executar o projeto

### 1. Clonar o repositório
```bash
git clone https://github.com/a-devrepo/cadastroAluno.git
cd cadastroAluno
```

### 2. Subir o banco de dados com Docker
Certifique-se de ter o **Docker** e **Docker Compose** instalados.  
Na raiz do projeto, execute:
```bash
docker-compose up -d
```
Isso irá criar e executar um container com PostgreSQL, configurado para o projeto.

### 3. Configurar o driver JDBC no Eclipse
O projeto requer o **driver JDBC do PostgreSQL** (`postgresql-<versão>.jar`).

Para configurá-lo:
1. Baixe o arquivo JAR do driver no site oficial ou no Maven Central.
2. Coloque-o em uma pasta do projeto (ex.: `libs/`).
3. No Eclipse, clique com o botão direito no projeto → **Build Path** → **Add JARs** → selecione o JAR.

### 4. Executar o script SQL
No container PostgreSQL ou em um cliente SQL, execute o script presente em `scripts/` para criar a tabela `aluno`. Via terminal:
```bash
docker exec -it NOME_DO_CONTAINER psql -U USUARIO -d NOME_DO_BANCO -f /caminho/do/script.sql
```

### 5. Executar a aplicação
No Eclipse:
- Localize a classe `Main` no pacote `principal`.
- Execute como **Java Application**.

---

## Observações
- Certifique-se de que as credenciais de conexão no `ConnectionFactory` correspondem às configuradas no `docker-compose.yml`.
- O projeto segue boas práticas de **SOLID** e **Clean Code**, mesmo sendo um exemplo simples.
