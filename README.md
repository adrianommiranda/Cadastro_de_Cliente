# 📋 Cadastro de Cliente

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📖 Sobre o Projeto

**Cadastro_de_Cliente** é uma API REST desenvolvida com Spring Boot seguindo o padrão de arquitetura em camadas.

A aplicação foi criada durante o curso na **Fuctura**, com foco em Spring Boot e Spring Data JPA, onde o principal objetivo foi a implementação de um CRUD completo. O sistema simula um cadastro de clientes com funcionalidades de busca e relatórios.

---

## 📝 Notas sobre o Projeto

Projeto desenvolvido durante o curso de Java na Fuctura, com o objetivo de aprender sobre desenvolvimento de APIs REST. Após o curso, o projeto recebeu atualizações para consolidar os conhecimentos adquiridos, incluindo:

- Implementação de DTOs (Request/Response)
- Validações com Bean Validation
- Tratamento global de exceções
- Documentação com Swagger/OpenAPI
- Consultas personalizadas com Spring Data JPA

---

## ⚙️ Funcionalidades

### CRUD de Clientes
- ✅ Criar cliente
- ✅ Atualizar cliente por ID
- ✅ Excluir cliente por ID
- ✅ Excluir cliente por nome
- ✅ Listar todos os clientes
- ✅ Buscar cliente por ID

### Buscas e Filtros
- 🔍 Buscar por nome exato
- 🔍 Pesquisar contendo termo
- 🔍 Pesquisar começando com prefixo
- 🔍 Pesquisar por sobrenome
- 🔍 Listar em ordem alfabética

### Relatórios por Data
- 📅 Buscar por data de cadastro
- 📅 Buscar por período (data início e fim)
- 📅 Buscar cadastrados após data
- 📅 Buscar cadastrados antes de data
- 📅 Buscar por data ordenado por nome

### Adicionais
- 📊 Retornar total de clientes cadastrados

---

## 📚 Documentação

### Cadastro de Cliente

O sistema possui funcionalidade de cadastro de clientes, com as seguintes informações:

| Campo | Tipo | Obrigatório | Descrição |
|-------|------|-------------|-----------|
| nome | String | ✅ Sim | Nome do cliente |
| sobrenome | String | ✅ Sim | Sobrenome do cliente |
| idade | Integer | ✅ Sim | Idade do cliente |
| descricao | String | ❌ Não | Descrição ou observações |

### Atualização do Cliente

O sistema permite atualização dos dados cadastrais. Campos atualizáveis:
- Nome
- Sobrenome
- Idade
- Descrição

### Exclusão do Cliente

O sistema possui duas formas de exclusão:
- **Por ID:** Exclusão direta pelo identificador único
- **Por Nome:** Exclusão pelo nome do cliente

---

## 🔗 Endpoints

### CRUD

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/clientes` | Criar novo cliente |
| PUT | `/api/clientes/{id}` | Atualizar cliente |
| DELETE | `/api/clientes/{id}` | Excluir por ID |
| DELETE | `/api/clientes/nome?nome=valor` | Excluir por nome |

### Listagens

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Listar todos os clientes |
| GET | `/api/clientes/{id}` | Buscar por ID |
| GET | `/api/clientes/buscar-por-nome?nome=valor` | Buscar por nome exato |
| GET | `/api/clientes/pesquisar-contendo?termo=valor` | Pesquisar contendo termo |
| GET | `/api/clientes/pesquisar-comecando-com?prefixo=valor` | Pesquisar começando com |
| GET | `/api/clientes/pesquisar-sobrenome?sobrenome=valor` | Pesquisar por sobrenome |
| GET | `/api/clientes/listar-ordem-alfabetica` | Listar em ordem alfabética |

### Relatórios por Data

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes/por-data-cadastro?data=yyyy-MM-dd` | Buscar por data específica |
| GET | `/api/clientes/por-periodo?dataInicio=yyyy-MM-dd&dataFim=yyyy-MM-dd` | Buscar por período |
| GET | `/api/clientes/cadastrados-apos?data=yyyy-MM-dd` | Buscar cadastrados após data |
| GET | `/api/clientes/cadastrados-antes?data=yyyy-MM-dd` | Buscar cadastrados antes de data |
| GET | `/api/clientes/por-data-ordenado-nome?data=yyyy-MM-dd` | Buscar por data ordenado por nome |

### Adicionais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes/total` | Retornar total de clientes |

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 21 | Linguagem de programação |
| Spring Boot | 3.x | Framework principal |
| Spring MVC | - | Camada de controle REST |
| Spring Data JPA | - | Persistência de dados |
| Hibernate | - | ORM (Mapeamento objeto-relacional) |
| Maven | - | Gerenciador de dependências |
| PostgreSQL | 16 | Banco de dados relacional |
| Lombok | - | Redução de código boilerplate |
| ModelMapper | - | Mapeamento de objetos (Entity ↔ DTO) |
| Swagger/OpenAPI | 3 | Documentação da API |

---

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ☕ **Java Development Kit (JDK) 21** ou superior
- 💻 **VS Code** ou outra IDE de sua preferência
- 🐘 **PostgreSQL** (instalação local ou remota)
- 📬 **Postman** (para testar a API)

---

## 🚀 Primeiros Passos

### 1. Clonagem do Repositório

```bash
git clone https://github.com/adrianommiranda/Cadastro_de_Cliente.git
cd Cadastro_de_Cliente

2. Configuração do Banco de Dados
Crie o banco de dados no PostgreSQL:

CREATE DATABASE cadastro;

3. Configuração da Aplicação
Abra o arquivo src/main/resources/application.yaml e configure:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cadastro
    username: postgres
    password: admin
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

4. Executando a Aplicação
bash

# Com Maven
./mvnw spring-boot:run

# Ou compile e execute
./mvnw clean package
java -jar target/Cadastro_de_Cliente-0.0.1-SNAPSHOT.jar

🌐 URLs da Aplicação

| Recurso | URL |
| --- | --- |
| API Base | http://localhost:8080/api/clientes |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/v3/api-docs |

🧪 Testando a API
Requisição POST - Cadastrar Clientes
URL: POST http://localhost:8080/api/clientes

Header: Content-Type: application/json

Cliente 1 - jason
{
    "nome": "Adriano",
    "sobrenome": "Miranda",
    "idade": 28,
    "descricao": "Desenvolvedor Java Spring Boot com PostgreSQL"
}

Cliente 2 - jason
{
    "nome": "Maria",
    "sobrenome": "Silva",
    "idade": 32,
    "descricao": "Analista de sistemas especializada em banco de dados"
}

Cliente 3 - jason
{
    "nome": "João",
    "sobrenome": "Santos",
    "idade": 45,
    "descricao": "Gerente de projetos com experiência em metodologias ágeis"
}

Cliente 4 - jason
{
    "nome": "Ana",
    "sobrenome": "Oliveira",
    "idade": 25,
    "descricao": "Desenvolvedora frontend React e TypeScript"
}

Cliente 5 - jason
{
    "nome": "Carlos",
    "sobrenome": "Pereira",
    "idade": 38,
    "descricao": "Arquiteto de software com foco em microserviços"
}

Cliente 6 - jason
{
    "nome": "Fernanda",
    "sobrenome": "Costa",
    "idade": 29,
    "descricao": "Especialista em DevOps e cloud computing AWS"
}

📁 Estrutura do Projeto
src/main/java/com/miranda/Cadastro_de_Cliente/
├── controller/
│   └── ClienteController.java
├── dto/
│   ├── ClienteRequestDTO.java
│   └── ClienteResponseDTO.java
├── entity/
│   └── Cliente.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/
│   └── ClienteRepository.java
├── service/
│   └── ClienteService.java
└── CadastroDeClienteApplication.java

👨‍💻 Autor
Adriano Miranda

GitHub adrianommiranda

MIT license