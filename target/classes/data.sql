INSERT INTO administradores (nome, email, senha, cpf)
VALUES 
    ('João da Silva', 'joao.silva@example.com', 'senhaSegura123', '123.456.789-00'),
    ('Maria Oliveira', 'maria.oliveira@example.com', 'senhaForte456', '987.654.321-00'),
    ('Klayvert de Andrade', 'klayvert@gmail.com', '123', '165.227.176-70')
ON DUPLICATE KEY UPDATE
    nome = VALUES(nome),
    email = VALUES(email),
    senha = VALUES(senha);

INSERT INTO clientes (nome, email, senha, cpf, estado, cidade, telefone)
VALUES
    ('Carlos Souza', 'carlos.souza@example.com', 'senhaCarlos789', '321.654.987-00', 'São Paulo', 'São Paulo', '(11) 91234-5678'),
    ('Ana Santos', 'ana.santos@example.com', 'senhaAna654', '456.789.123-00', 'Rio de Janeiro', 'Rio de Janeiro', '(21) 92345-6789'),
    ('Klayvert', 'klay@gmail.com', '123', '165.227.176.70', 'Paraíba', 'João Pessoa', '(31) 97360-9900')
ON DUPLICATE KEY UPDATE
    nome = VALUES(nome),
    email = VALUES(email),
    senha = VALUES(senha),
    estado = VALUES(estado),
    cidade = VALUES(cidade),
    telefone = VALUES(telefone);

INSERT INTO produtos (nome, descricao, preco, categoria, imagem_url, quantidade_estoque)
VALUES

    -- Carnes
    ('Picanha', 'Picanha grelhada no ponto ideal', 79.90, 'Carnes', '/img/picanha.jpeg', 50), -- ok
    ('Maminha', 'Maminha suculenta assada na brasa', 64.90, 'Carnes', '/img/maminha.jpg', 50), -- ok
    ('Fraldinha', 'Fraldinha macia e saborosa', 69.90, 'Carnes', '/img/fraldinha.jpg', 50), -- ok
    ('Costela', 'Costela bovina assada lentamente', 74.90, 'Carnes', '/img/costela.jpg', 50), -- ok
    ('Alcatra', 'Alcatra grelhada no espeto', 59.90, 'Carnes', '/img/alcatra.jpg', 50), -- ok
    ('Cupim', 'Cupim com toque de ervas finas', 72.90, 'Carnes', '/img/cupim.jpg', 50), -- ok
    ('Cordeiro', 'Pernil de cordeiro assado no ponto', 89.90, 'Carnes', '/img/cordeiro.jpg', 50), -- ok
    ('Frango', 'Frango (Coração, Asa, Coxa) temperado e grelhado', 34.90, 'Carnes', '/img/frango.jpg', 50), -- ok
    ('Linguiça', 'Linguiça de pernil suculenta grelhada', 29.90, 'Carnes', '/img/linguica.jpg', 50), -- ok
    ('Salmão', 'Salmão grelhado com toque de limão', 95.90, 'Carnes', '/img/salmao.jpg', 50), -- ok
    ('Tilápia', 'Tilápia grelhada ao molho de ervas', 49.90, 'Carnes', '/img/tilapia.jpg', 50), -- ok

    -- Acompanhamentos
    ('Arroz', 'Arroz branco soltinho', 9.90, 'Acompanhamentos', '/img/arroz.png', 100), -- ok
    ('Feijão Tropeiro', 'Feijão tropeiro com bacon e farinha de mandioca', 14.90, 'Acompanhamentos', '/img/feijao-tropeiro.jpg', 100), -- ok
    ('Farofa', 'Farofa crocante com bacon', 12.90, 'Acompanhamentos', '/img/farofa.jpg', 100), -- ok
    ('Batata Frita', 'Batata frita crocante', 15.90, 'Acompanhamentos', '/img/batata-frita.jpg', 100), -- ok
    ('Salada de Maionese', 'Maionese com batatas e cenoura', 16.90, 'Acompanhamentos', '/img/salada-de-maionese.jpeg', 100), -- ok
    ('Polenta Frita', 'Polenta frita crocante', 13.90, 'Acompanhamentos', '/img/polenta.jpg', 100), -- ok
    ('Mandioca Frita', 'Mandioca frita crocante', 14.90, 'Acompanhamentos', '/img/mandioca.jpg', 100), -- ok

    -- Bebidas
    ('Cerveja Artesanal', 'Seleção de cervejas artesanais locais', 19.90, 'Bebidas', '/img/cerveja-artesanal.jpg', 100), -- ok
    ('Vinho', 'Taça de vinho tinto ou branco', 29.90, 'Bebidas', '/img/vinho.png', 100), -- ok
    ('Caipirinha', 'Tradicional caipirinha brasileira', 18.90, 'Bebidas', '/img/caipirinha.jpg', 100), -- ok
    ('Refrigerante', 'Refrigerantes variados (lata)', 7.90, 'Bebidas', '/img/refrigerante.jpg', 100), -- ok
    ('Suco Natural', 'Suco de frutas frescas', 9.90, 'Bebidas', '/img/suco.jpg', 100), -- ok
    ('Água com Gás', 'Água mineral com gás', 5.90, 'Bebidas', '/img/agua-com-gas.jpg', 100), -- ok
    ('Água sem Gás', 'Água mineral sem gás', 4.90, 'Bebidas', '/img/agua.jpg', 100), --ok

    -- Molhos
    ('Chimichurri', 'Molho argentino à base de ervas', 4.90, 'Molhos', '/img/chimichurri.jpg', 50), -- ok
    ('Molho de Alho', 'Molho cremoso de alho', 3.90, 'Molhos', '/img/molho-de-alho.png', 50), -- ok
    ('Pimenta', 'Molho de pimenta tradicional', 3.50, 'Molhos', '/img/pimenta.jpg', 50), -- ok
    ('Vinagrete', 'Molho vinagrete de tomate e cebola', 4.90, 'Molhos', '/img/vinagrete.jpg', 50) -- ok

ON DUPLICATE KEY UPDATE
    descricao = VALUES(descricao),
    preco = VALUES(preco),
    categoria = VALUES(categoria),
    imagem_url = VALUES(imagem_url),
    quantidade_estoque = VALUES(quantidade_estoque);


INSERT INTO vendas (cliente_id, administrador_id, valor_total, metodo_pagamento, data_venda) VALUES
(1, 1, 50.00, 'Cartão de Crédito', '2024-10-15 14:30:00'),
(2, 2, 75.00, 'PIX', '2024-10-16 10:15:00'),
(1, 1, 30.00, 'Berries', '2024-10-17 11:45:00')
ON DUPLICATE KEY UPDATE
    valor_total = VALUES(valor_total),
    metodo_pagamento = VALUES(metodo_pagamento),
    data_venda = VALUES(data_venda);

INSERT INTO item_vendas (venda_id, produto_id, quantidade, preco_unitario) VALUES
(1, 1, 2, 79.90),  -- 2 unidades de Picanha
(1, 2, 2, 9.90),   -- 2 unidades de Arroz
(1, 3, 1, 14.90),  -- 1 unidade de Feijão Tropeiro
(1, 4, 1, 18.90),  -- 1 unidade de Caipirinha

(2, 1, 1, 79.90),  -- 1 unidade de Picanha
(2, 5, 1, 29.90),  -- 1 unidade de Vinho

(3, 1, 2, 79.90),  -- 2 unidades de Picanha
(3, 2, 3, 9.90),   -- 3 unidades de Arroz
(3, 4, 2, 18.90)  -- 2 unidades de Caipirinha

ON DUPLICATE KEY UPDATE
    quantidade = VALUES(quantidade),
    preco_unitario = VALUES(preco_unitario);