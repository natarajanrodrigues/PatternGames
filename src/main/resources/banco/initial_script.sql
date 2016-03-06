/*
--configurações gerais para criação do banco
CREATE DATABASE sislivros
  WITH ENCODING='UTF8'
       OWNER=postgres
       LC_COLLATE='pt_BR.UTF-8'
       LC_CTYPE='pt_BR.UTF-8'
       CONNECTION LIMIT=-1;
*/


/*
table usuario

Requistos básicos
nome, cpf e email
*/

CREATE TABLE Cliente(
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    PRIMARY KEY(cpf)

);


CREATE TABLE Jogo(
    id SERIAL NOT NULL, 
    nome VARCHAR(100) NOT NULL,
    genero VARCHAR(100) NOT NULL, 
    isDisponivel BOOLEAN DEFAULT TRUE,
    PRIMARY KEY(id)

);


CREATE TABLE Observacoes(
    idJogo INTEGER NOT NULL, 
    cpfCliente VARCHAR(14) NOT NULL,
    PRIMARY KEY(idJogo, cpfCliente)
);

CREATE TABLE TiposLocacao(
    id INTEGER NOT NULL, 
    nome VARCHAR(10) NOT NULL, 
    PRIMARY KEY(id)

);

CREATE TABLE Locacao(
    id SERIAL NOT NULL, 
    cpfCliente VARCHAR(14) NOT NULL,
    idJogo INTEGER NOT NULL, 
    dataLocacao DATE NOT NULL, 
    dataDevolucao DATE, 
    valorPago DOUBLE PRECISION DEFAULT 0, 
    tipo INT NOT NULL, 
    PRIMARY KEY (id)
);


INSERT INTO TiposLocacao(id, nome) VALUES (1, 'COMUM');
INSERT INTO TiposLocacao(id, nome) VALUES (2, 'ESPECIAL');

INSERT INTO Jogo(nome, genero) VALUES ('Donkey Kong', 'aventura');
INSERT INTO Jogo(nome, genero) VALUES ('Super Mario Bros', 'aventura');
INSERT INTO Jogo(nome, genero) VALUES ('Mario Kart', 'corrida');
INSERT INTO Jogo(nome, genero) VALUES ('Street Fighter IV - Alpha', 'luta');

