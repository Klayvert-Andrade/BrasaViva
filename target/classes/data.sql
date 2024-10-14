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
    ('Picanha', 'Picanha grelhada no ponto ideal', 79.90, 'Carnes', '/img/picanha.jpeg', 50),
    ('Maminha', 'Maminha suculenta assada na brasa', 64.90, 'Carnes', '/img/maminha.jpg', 50),
    ('Fraldinha', 'Fraldinha macia e saborosa', 69.90, 'Carnes', '/img/fraldinha.jpg', 50),
    ('Costela', 'Costela bovina assada lentamente', 74.90, 'Carnes', '/img/costela.jpg', 50),
    ('Alcatra', 'Alcatra grelhada no espeto', 59.90, 'Carnes', '/imagens/alcatra.jpg', 50),
    ('Cupim', 'Cupim com toque de ervas finas', 72.90, 'Carnes', '/imagens/cupim.jpg', 50),
    ('Cordeiro', 'Pernil de cordeiro assado no ponto', 89.90, 'Carnes', '/imagens/cordeiro.jpg', 50),
    ('Frango', 'Frango (Coração, Asa, Coxa) temperado e grelhado', 34.90, 'Carnes', '/imagens/frango.jpg', 50),
    ('Linguiça', 'Linguiça de pernil suculenta grelhada', 29.90, 'Carnes', '/imagens/linguica.jpg', 50),
    ('Salmão', 'Salmão grelhado com toque de limão', 95.90, 'Carnes', '/imagens/salmao.jpg', 50),
    ('Tilápia', 'Tilápia grelhada ao molho de ervas', 49.90, 'Carnes', '/imagens/tilapia.jpg', 50),

    -- Acompanhamentos
    ('Arroz', 'Arroz branco soltinho', 9.90, 'Acompanhamentos', '/img/arroz.jpg', 100),
    ('Feijão Tropeiro', 'Feijão tropeiro com bacon e farinha de mandioca', 14.90, 'Acompanhamentos', '/imagens/feijao_tropeiro.jpg', 100),
    ('Farofa', 'Farofa crocante com bacon', 12.90, 'Acompanhamentos', '/imagens/farofa.jpg', 100),
    ('Batata Frita', 'Batata frita crocante', 15.90, 'Acompanhamentos', '/imagens/batata_frita.jpg', 100),
    ('Salada de Maionese', 'Maionese com batatas e cenoura', 16.90, 'Acompanhamentos', '/imagens/salada_maionese.jpg', 100),
    ('Polenta Frita', 'Polenta frita crocante', 13.90, 'Acompanhamentos', '/imagens/polenta_frita.jpg', 100),
    ('Mandioca Frita', 'Mandioca frita crocante', 14.90, 'Acompanhamentos', '/imagens/mandioca_frita.jpg', 100),

    -- Bebidas

    ('Cerveja Artesanal', 'Seleção de cervejas artesanais locais', 19.90, 'Bebidas', '/imagens/cerveja_artesanal.jpg', 100),
    ('Vinho', 'Taça de vinho tinto ou branco', 29.90, 'Bebidas', '/imagens/vinho.jpg', 100),
    ('Caipirinha', 'Tradicional caipirinha brasileira', 18.90, 'Bebidas', '/imagens/caipirinha.jpg', 100),
    ('Refrigerante', 'Refrigerantes variados (lata)', 7.90, 'Bebidas', '/imagens/refrigerante.jpg', 100),
    ('Suco Natural', 'Suco de frutas frescas', 9.90, 'Bebidas', '/imagens/suco_natural.jpg', 100),
    ('Água com Gás', 'Água mineral com gás', 5.90, 'Bebidas', '/imagens/agua_com_gas.jpg', 100),
    ('Água sem Gás', 'Água mineral sem gás', 4.90, 'Bebidas', '/imagens/agua_sem_gas.jpg', 100),

    -- Molhos
    ('Chimichurri', 'Molho argentino à base de ervas', 4.90, 'Molhos', '/imagens/chimichurri.jpg', 50),
    ('Molho de Alho', 'Molho cremoso de alho', 3.90, 'Molhos', '/imagens/molho_de_alho.jpg', 50),
    ('Pimenta', 'Molho de pimenta tradicional', 3.50, 'Molhos', '/imagens/pimenta.jpg', 50),
    ('Vinagrete', 'Molho vinagrete de tomate e cebola', 4.90, 'Molhos', '/imagens/vinagrete.jpg', 50)

ON DUPLICATE KEY UPDATE
    descricao = VALUES(descricao),
    preco = VALUES(preco),
    categoria = VALUES(categoria),
    imagem_url = VALUES(imagem_url),
    quantidade_estoque = VALUES(quantidade_estoque);