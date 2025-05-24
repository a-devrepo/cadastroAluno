# Projeto: Cadastro de Alunos (CRUD)

## 📚 Descrição

Este é um projeto simples de cadastro de alunos implementado com **Java 17**, utilizando **JDBC** para acesso ao banco de dados **PostgreSQL**. O banco de dados é executado dentro de um container **Docker**, e a aplicação segue uma arquitetura em camadas para garantir organização, coesão e separação de responsabilidades.

---

## ⚙️ Tecnologias e Ferramentas

- Java 17
- JDBC (Java Database Connectivity)
- PostgreSQL (em container Docker)
- Docker / Docker Compose
- IDE (Eclipse)

---
## 🧱 Arquitetura de Camadas

- **`principal`**: Ponto de entrada do programa, invoca o menu da aplicação.
- **`controllers`**: Interpreta as opções do usuário e delega ações para o service.
- **`service`**: Contém a lógica de negócio e orquestra a comunicação entre controller e repositório.
- **`validators`**: Validação de regras de negócio e integridade de dados.
- **`views`**: Responsável por exibir informações ao usuário (neste caso, via console).
- **`repositories`**: Acesso direto ao banco de dados com comandos SQL (CRUD).
- **`factories`**: Contém a interface `ConnectionFactory`, que fornece conexões com o banco PostgreSQL.
- **`exceptions`**: Exceções customizadas para tratar erros específicos de negócio ou de persistência.
- **`scripts`**: Scripts SQL para criação da tabela `aluno`.
