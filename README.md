# BrasaViva

Este é um projeto de um sistema de vendas de uma churrascaria para a cadeira de Banco de Dados do curso de Ciência da Computação da UFPB - 2024.1

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- Java JDK 17 ou superior
- Maven
- MySQL

## Configuração do Banco de Dados

1. **Criar o banco de dados:**
   - Abra o MySQL e execute o seguinte comando para criar o banco de dados:

     ```sql
     CREATE DATABASE fruitables;
     ```
  
2. **Configurar o banco de dados:**
   - Localize o arquivo `src/main/resources/application.properties` e edite as seguintes configurações de conexão com o banco de dados:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/fruitables?useTimezone=true&serverTimezone=UTC
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     ```

   - Substitua `SEU_USUARIO` e `SUA_SENHA` pelas credenciais do seu banco de dados MySQL.
  
3. **Executar scripts de inicialização:**
   - Verifique se os arquivos `schema.sql` e `data.sql` estão localizados em `src/main/resources/`. O `schema.sql` deve conter as instruções para criar as tabelas necessárias, e o `data.sql` deve conter as inserções de dados iniciais.


## Inicializando o Projeto

Após configurar o banco de dados, siga as etapas abaixo para iniciar o projeto:

1. **Baixar o repositório:**
   - Clone o repositório e mude o nome da pasta "BrasaViva" para "Fruitables".

2. **Construir o projeto:**
   - Execute o seguinte comando para compilar o projeto e instalar as dependências:

     ```bash
     ./mvnw clean install
     ```    

3. **Executar o projeto:**
   - Após a construção bem-sucedida, inicie o aplicativo com o comando:

     ```bash
     ./mvnw spring-boot:run
     ```
4. **Acessar a aplicação:**
   - Abra um navegador da web e acesse o seguinte URL:

     ```
     http://localhost:8080/home
     ```
