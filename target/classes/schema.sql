-- Criação da tabela administradores
CREATE TABLE IF NOT EXISTS administradores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(180) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) NOT NULL UNIQUE
);

-- Criação da tabela clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(180) NOT NULL,
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


-- Criação da tabela pagamentos (relacionada à tabela de vendas)
CREATE TABLE IF NOT EXISTS pagamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    metodo_pagamento VARCHAR(50) NOT NULL,
    valor_total DOUBLE NOT NULL,
    data_pagamento DATETIME NOT NULL
);

-- Criação da tabela vendas
CREATE TABLE IF NOT EXISTS vendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    administrador_id INT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    pagamento_id INT,
    CONSTRAINT fk_venda_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_venda_administrador FOREIGN KEY (administrador_id) REFERENCES administradores(id),
    CONSTRAINT fk_venda_pagamento FOREIGN KEY (pagamento_id) REFERENCES pagamentos(id)
);

-- Criação da tabela item_carrinho (relacionada às tabelas produtos e carrinho)
CREATE TABLE IF NOT EXISTS item_carrinho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produto_id INT NOT NULL,
    carrinho_id INT NOT NULL,
    quantidade INT NOT NULL,
    CONSTRAINT fk_item_carrinho_produto FOREIGN KEY (produto_id) REFERENCES produtos(id),
    CONSTRAINT fk_item_carrinho_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho(id)
);

