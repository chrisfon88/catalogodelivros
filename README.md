# 📚 Catálogo de Livros com API Gutendex
Bem-vindo(a) ao projeto Catálogo de Livros com API Gutendex! Este repositório contém uma aplicação Java com Spring Boot que permite a busca, registro e listagem de livros e autores utilizando dados provenientes da API pública Gutendex, com armazenamento em um banco de dados PostgreSQL.

# 📖 Sobre o Projeto
Este projeto faz parte do desafio do Programa Oracle Next Education para aprimorar conhecimentos em Java e Spring Boot. O objetivo é criar um sistema funcional que consulte uma API externa, registre os dados em um banco de dados e ofereça diferentes funcionalidades de consulta aos usuários.

Por meio deste catálogo, é possível explorar informações sobre livros e autores, além de manter os dados persistidos localmente para facilitar consultas futuras sem necessidade de chamadas contínuas à API externa.

# 🚀 Tecnologias Utilizadas
- **Java 17:** Linguagem de programação utilizada para construir a aplicação.
- **Spring Boot:** Framework para desenvolvimento rápido de aplicações web Java.
- **Spring Data JPA:** Para acesso ao banco de dados relacional com mapeamento objeto-relacional (ORM).
- **PostgreSQL:** Banco de dados relacional utilizado para armazenar as informações de livros e autores.
- **REST Template:** Para realizar chamadas à API externa Gutendex.
- **Maven:** Gerenciador de dependências e build do projeto.

# 🖥️ Funcionalidades
### 1. Buscar Livro pelo Título

   Realiza uma busca na API Gutendex pelo título do livro e salva os resultados no banco de dados local. 
   
**📋 Exemplo de Resultado:**

   ```LivroDTO[titulo=Great Expectations, nomeAutor=Dickens, Charles, idioma=en, numeroDownloads=19993]```

### 2. Listar Livros Registrados

   Exibe todos os livros que já foram salvos no banco de dados.

### 3. Listar Autores Registrados
   
Exibe a lista de autores únicos cadastrados no banco de dados.

### 4. Listar Livros por Idioma

   Permite listar livros armazenados de acordo com o idioma (como ```pt``` para português, ```en``` para inglês, etc.).
   
Se não houver livros no idioma solicitado, uma mensagem será exibida:

   ```"Não existe livros nesse idioma no banco de dados."```

# 🔧 Configuração do Ambiente

**Pré-requisitos:**

- **Java 17** instalado

- **PostgreSQL** configurado e com um banco de dados criado

- **Maven** instalado

**Configuração do arquivo ```application.properties```:**

```spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}```

```spring.datasource.username=${DB_USER}```

```spring.datasource.password=${DB_PASSWORD}```

```spring.datasource.driver-class-name=org.postgresql.Driver```

```spring.jpa.hibernate.ddl-auto=update```

```spring.jpa.show-sql=true```

```spring.jpa.format-sql=true```

**Substitua ```${DB_HOST}```, ```${DB_NAME}```, ```${DB_USER}```, e ```${DB_PASSWORD}``` com suas configurações locais de banco de dados PostgreSQL.**

# ▶️ Como Executar o Projeto

### 1. Clone este repositório:

```git clone <url_do_repositorio>```

```cd catalogo-livros```

### 2. Compile e execute a aplicação:

```mvn clean install```

```mvn spring-boot:run```

### 3. Acesse o terminal para interagir com as funcionalidades:

Escolha o número de sua opção:

1 - Buscar livro pelo título

2 - Listar livros registrados

3 - Listar autores registrados

4 - Listar livros em um determinado idioma

0 - Sair

# 🏆 Conclusão

Este projeto é uma aplicação prática que demonstra a integração de diferentes tecnologias para resolver problemas reais. Além de aprimorar o conhecimento em desenvolvimento Back-End com Spring Boot e bancos de dados, o projeto também evidencia a importância de utilizar APIs externas de forma eficiente.

Sinta-se à vontade para contribuir, enviar feedbacks ou explorar este repositório!