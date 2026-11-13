# 📦 Sistema de Gestão de Estoque (ProjetoSI)

## 1. Informações do Projeto

* **Nome do Sistema:** API de Gestão de Estoque (`ProjetoSI`)
* **Integrantes do Grupo:**
  * André Luis Leite Haji
  * Emanuel Rodrigues Cardoso
  * João Antônio Vasconcellos de Sá
  * João Rafael Araújo Serra
  * João Guilherme Almeida Lira da Silva
  * Luiz Gustavo Pereira da Silva
  * 
**Descrição do Objetivo:**  
  Desenvolver uma API RESTful para controle e gerenciamento de produtos em estoque, permitindo o cadastro, validação de regras de negócio (como bloqueio de preços inválidos) e consulta de itens persistidos em banco de dados relacional. O projeto aplica arquitetura em camadas com DTOs, testes unitários automatizados e containerização completa para garantir portabilidade entre ambientes.

---

## 2. Tecnologias Utilizadas

* **Java 21** (Eclipse Temurin)
* **Spring Boot** (Spring Web, Spring Data JPA)
* **PostgreSQL 16** (Banco de dados relacional)
* **Docker & Docker Compose** (Containerização e orquestração da API e do banco)
* **JUnit 5 & Mockito** (Testes automatizados unitários)
* **GitHub Actions** (Integração e entrega contínua - CI/CD)

---

## 3. Como Executar

### Pré-requisitos
* Docker e Docker Compose instalados
* Git

### Passo a passo para iniciar o projeto

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/Ulmane-RC/ProjetoSI.git](https://github.com/Ulmane-RC/ProjetoSI.git)
   cd ProjetoSI
   
2. .env

3. **Suba os containers da aplicação e do banco de dados:**
   ```bash
   docker compose up --build -d
   ```

4. **Verifique se os serviços estão rodando:**
   ```bash
   docker ps
   ```
   A API estará disponível em `http://localhost:8080`.

5. **Para parar e remover os containers:**
   ```bash
   docker compose down
   ```

### Executando os Testes Automatizados

* **Testes unitários isolados (sem necessidade do banco ativo):**
  ```bash
  ./mvnw test -Dtest=ProdutoServiceTest
  ```

* **Todos os testes (com o container `postgres-db` ativo):**
  ```bash
  docker compose up -d postgres-db
  ```

---

## 4. Endpoints Disponíveis

### `GET /produtos`
Retorna a lista de todos os produtos cadastrados no estoque.

* **Exemplo de requisição:**
  ```bash
  curl http://localhost:8080/produtos
  ```

* **Exemplo de resposta (`200 OK`):**
  ```json
  [
    {
      "id": 1,
      "descricao": "Notebook Dell",
      "categoria": "INFORMATICA",
      "preco": 3500.00,
      "quantidade": 2,
      "status": true
    }
  ]
  ```

### `POST /produtos`
Cadastra um novo produto no estoque.

* **Exemplo de requisição (`Linux / macOS`):**
  ```bash
  curl -X POST http://localhost:8080/produtos \
    -H "Content-Type: application/json" \
    -d '{"descricao": "Notebook Dell", "categoria": "INFORMATICA", "preco": 3500.00, "quantidade": 5, "status": true}'
  ```

* **Exemplo de requisição (`Windows PowerShell`):**
  ```powershell
  curl.exe -X POST http://localhost:8080/produtos -H "Content-Type: application/json" -d '{\"descricao\": \"Notebook Dell\", \"categoria\": \"INFORMATICA\", \"preco\": 3500.00, \"quantidade\": 5, \"status\": true}'
  ```

* **Resposta (`201 Created`):** Retorna o JSON do produto salvo com o `id` gerado automaticamente.