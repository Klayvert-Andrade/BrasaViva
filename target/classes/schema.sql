-- Criação da tabela administradores
CREATE TABLE IF NOT EXISTS administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(180) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE
);

-- Criação da tabela clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(180) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    estado VARCHAR(20) NOT NULL,
    cidade VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

-- Criação da tabela carrinho
CREATE TABLE IF NOT EXISTS carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    CONSTRAINT fk_carrinho_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Criação da tabela produtos
CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(180) NOT NULL UNIQUE,
    descricao VARCHAR(500) NOT NULL,
    preco DOUBLE NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    imagem_url VARCHAR(255),
    quantidade_estoque INT NOT NULL
);

-- Criação da tabela estoque (relacionada à tabela produtos)
CREATE TABLE IF NOT EXISTS estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Criação da tabela vendas
CREATE TABLE IF NOT EXISTS vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    administrador_id INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    metodo_pagamento VARCHAR(50),
    data_venda DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_venda_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_venda_administrador FOREIGN KEY (administrador_id) REFERENCES administradores(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Criação da tabela item_carrinho (relacionada às tabelas produtos e carrinho)
CREATE TABLE IF NOT EXISTS item_carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    carrinho_id INT NOT NULL,
    venda_id INT, -- Adicionando a coluna venda_id para vincular a venda
    quantidade INT NOT NULL,
    CONSTRAINT fk_item_carrinho_produto FOREIGN KEY (produto_id) REFERENCES produtos(id),
    CONSTRAINT fk_item_carrinho_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho(id),
    CONSTRAINT fk_item_carrinho_venda FOREIGN KEY (venda_id) REFERENCES vendas(id) -- Cria a relação com a tabela vendas
);

-- Criação da tabela item_vendas
CREATE TABLE IF NOT EXISTS item_vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_item_venda_venda FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_item_venda_produto FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE ON UPDATE CASCADE
);