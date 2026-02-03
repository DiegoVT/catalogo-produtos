# Catálogo de Produtos com Busca Inteligente (SQL + Busca)

**DISCIPLINA:** Novas Tecnologias de Banco de Dados  
**PROJETO FINAL – TURMA:** Sistemas para Internet  

✅ **GRUPO 1 — PROJETO 01**  
**CATÁLOGO DE PRODUTOS COM BUSCA INTELIGENTE (SQL + Busca)**

---

## 📌 Visão geral

Projeto full stack para **cadastro e consulta de produtos**, com **filtros**, **paginação** e **busca** via SQL, integrando:

- **Backend:** Java 17 + Spring Boot + Spring Data JPA + MySQL  
- **Frontend:** React (Vite) consumindo a API

---

## ✅ Funcionalidades

### Backend (API)
- Produtos:
  - `GET /produtos` (busca com `q`, filtros `marcaId`, `categoriaId`, `page`, `size`, `order`)
  - `GET /produtos/{id}` (detalhe + incrementa views)
  - `POST /produtos` (cadastra produto)
  - *(se implementado)* `PUT /produtos/{id}` / `DELETE /produtos/{id}`
- Marcas:
  - `GET /marcas` / `POST /marcas`
- Categorias:
  - `GET /categorias` / `POST /categorias`
- CORS liberado para permitir o front consumir a API

### Frontend (React)
- Formulário completo de produto:
  - `select` carregando `/marcas` e `/categorias`
  - botão **Salvar** chamando `POST /produtos`
- Tela de busca/listagem:
  - filtro por **texto (q)**, **marca**, **categoria**
  - paginação via `page/size`
  - lista atualiza automaticamente após salvar (reload)

---

## 🧱 Estrutura do projeto

> Ajuste os nomes das pastas conforme o seu repositório (ex.: `catalogo-produtos` e `catalogo-front`).

```
catalogo-produtos/               # backend (Spring Boot)
  src/main/java/br/com/diego/catalogo_produtos/
    controller/
    service/
    repository/
    model/
    dto/
    config/                      # CorsConfig.java
  src/main/resources/
    application.yml

catalogo-front/                  # frontend (React/Vite)
  src/
    api/http.js
    components/
      ProdutoForm.jsx
      ProdutoList.jsx
    App.jsx
```

---

## ⚙️ Pré-requisitos

### Backend
- Java **17**
- Maven (ou `mvnw`)
- MySQL **8.x**

### Frontend
- Node.js **LTS** + npm

---

## 🗄️ Banco de dados (MySQL)

Crie o banco:
```sql
CREATE DATABASE catalogo_produtos;
```

Configure o `application.yml` (exemplo):
```yml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/catalogo_produtos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: 1234

  jpa:
    hibernate:
      ddl-auto: update
    open-in-view: false
```

### (Opcional) FULLTEXT para busca
Se estiver usando `MATCH ... AGAINST`, crie o índice:
```sql
ALTER TABLE produto
  ADD FULLTEXT INDEX ft_produto_nome_descricao (nome, descricao);
```

---

## ▶️ Como rodar o BACKEND

Dentro da pasta do backend:
```bash
cd C:\catalogo-produtos\catalogo-produtos
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

API sobe em:
- `http://localhost:8081`

---

## ▶️ Como rodar o FRONTEND

Dentro da pasta do front:
```bash
cd C:\catalogo-produtos\catalogo-front
npm install
npm run dev
```

Front abre em:
- normalmente `http://localhost:5173`

---

## 🧪 Testes rápidos (Postman)

### Criar Marca
`POST http://localhost:8081/marcas`
```json
{ "nome": "Dell" }
```

### Criar Categoria
`POST http://localhost:8081/categorias`
```json
{ "nome": "Notebook" }
```

### Criar Produto
`POST http://localhost:8081/produtos`
```json
{
  "nome": "Notebook Dell i5",
  "descricao": "16GB RAM, SSD 512GB",
  "preco": 3999.90,
  "estoque": 10,
  "marcaId": 1,
  "categoriaId": 1
}
```

### Buscar com filtros/paginação
`GET http://localhost:8081/produtos?q=dell&marcaId=1&categoriaId=1&page=0&size=10`

---

## ✅ Status do projeto
- [x] Backend rodando e conectando no MySQL
- [x] Endpoints de marcas/categorias para alimentar o sistema
- [x] Front com form + listagem e filtros
- [x] Paginação no front usando `page/size`
- [x] Reload automático após salvar

---

## 📌 Próximas melhorias (opcional)
- Retornar paginação no backend com `Page<ProdutoResponse>`
- Adicionar PUT/DELETE no front (editar/remover)
- Melhorar layout (responsivo) e adicionar alerts
- Padronizar erros com `@ControllerAdvice`

---

## 👤 Autor
**Diego Vieira Torres e João Emanuel**
