# LiterAlura

O **LiterAlura** é uma aplicação Java desenvolvida como parte de um desafio de programação do programa ONE. O objetivo do projeto é gerenciar uma biblioteca de livros e autores, permitindo ao usuário buscar livros online, registrar novos livros e autores, e listar informações sobre livros e autores armazenados no sistema.

A aplicação se conecta a uma API (<a href="https://gutendex.com/">Gutendex</a>) para buscar dados de livros e armazena essas informações em um banco de dados local.

## Tecnologias utilizadas

[![Java](https://img.shields.io/badge/Java-E02027?style=for-the-badge&logo=java&logoColor=ffffff)](https://www.java.com/en/)

[![Spring](https://img.shields.io/badge/SrpingBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=ffffff)](https://spring.io/projects/spring-boot)

[![PostgreSQL](https://img.shields.io/badge/POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=ffffff)](https://www.postgresql.org/)

## Instalação

Note: tenha um banco de dados PostgreSQL para guardar as informações que viram da API. Você pode ajustar a conexão com o banco de dados no arquivo '_application.properties_' da pasta '_resources_'.

```shell
git clone 
```

## Funcionalidades

A aplicação possui um menu interativo com as seguintes opções:

1. **Buscar livro pelo título**:
    - Permite ao usuário procurar um livro pelo título usando uma API externa.
    - Se o livro não estiver registrado, ele será adicionado ao banco de dados junto com o autor, se ainda não estiver cadastrado.

2. **Listar livros registrados**:
    - Exibe todos os livros que foram registrados na aplicação.

3. **Listar autores registrados**:
    - Exibe todos os autores que foram registrados na aplicação.

4. **Listar autores vivos em um determinado ano**:
    - Permite ao usuário buscar e listar autores que estavam vivos em um ano específico.

5. **Listar livros em um determinado idioma**:
    - Permite ao usuário listar livros que estão registrados em um idioma específico (Espanhol, Inglês, Francês, Português).