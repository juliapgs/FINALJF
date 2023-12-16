-- Dados do banco de dados --

-- Usuários para login
insert into usuario (nome, cpf, email, login, senha, perfil) values('Julia Pereira','089433','julia@admin.com','julha', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);
insert into usuario (nome, cpf, email, login, senha, perfil) values('Felipe Ferreira','024100','felipe@admin.com','lipe', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 2);
insert into usuario (nome, cpf, email, login, senha, perfil) values('Gabriel Alves','987522','gabriel@cliente.com','biel', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into usuario (nome, cpf, email, login, senha, perfil) values('Janio Prof','134679','janio@cliente.com','janio', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);
insert into usuario (nome, cpf, email, login, senha, perfil) values('Esther Mota','975842','esther@cliente.com','ste', 'yEaSZv1mx2Hf11tomtEAY3HUG2hrQS2ACE17U1PeCoA7PFIhHARbDredPke5UTKwvMVA+jod2rMVKSoDzm8p3Q==', 1);

-- Numeros de telefone
insert into telefone (codigoArea, numero) values('63', '9999-9999');
insert into telefone (codigoArea, numero) values('62', '8888-8888');
insert into telefone (codigoArea, numero) values('61', '7777-7777');
insert into telefone (codigoArea, numero) values('67', '6666-6666');
insert into telefone (codigoArea, numero) values('11', '5555-5555');
insert into telefone (codigoArea, numero) values('77', '4444-4444');

-- Relação UsuarioTelefone
insert into usuario_telefone (id_usuario, id_telefone) values(1, 1);
insert into usuario_telefone (id_usuario, id_telefone) values(1, 5);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 2);
insert into usuario_telefone (id_usuario, id_telefone) values(2, 6);
insert into usuario_telefone (id_usuario, id_telefone) values(3, 3);
insert into usuario_telefone (id_usuario, id_telefone) values(4, 4);

-- Estados 
insert into estado (nomeEstado, sigla) values('Tocantins', 'TO');
insert into estado (nomeEstado, sigla) values('Bahia', 'BA');
insert into estado (nomeEstado, sigla) values('São Paulo', 'SP');
insert into estado (nomeEstado, sigla) values('Rio de Janeiro', 'RJ');

-- Cidades
insert into cidade (nomeCidade, id_estado) values('Palmas', 1);
insert into cidade (nomeCidade, id_estado) values('Paraíso', 1);
insert into cidade (nomeCidade, id_estado) values('Salvador', 2);
insert into cidade (nomeCidade, id_estado) values('Barreiras', 2);
insert into cidade (nomeCidade, id_estado) values('Rio de Janeiro', 4);
insert into cidade (nomeCidade, id_estado) values('Santos', 3);

-- Enderecos
insert into endereco (cep, logradouro, numero, complemento, id_cidade) values('77023-382', 'Quadra 904 Sul', 11, 'alameda 14', 1);
insert into endereco (cep, logradouro, numero, complemento, id_cidade) values('77010-000', 'Quadra 106 Norte', 10,'Condomínio Ferreira', 2);
insert into endereco (cep, logradouro, numero, complemento, id_cidade) values('77006-500', 'Quadra 204 Sul', 13, 'alameda 02', 3);

-- Inserindo Tenis
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Nike', 'Air Force 1', 'CASUAL', 'Branco', 41, 799.99, 10);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Nike', 'Dunk Low SB', 'SKATE', 'Preto', 40, 899.99, 5);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Nike', 'Jordan 1', 'BASQUETE', 'Vermelho', 44, 1299.99, 3);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Adidas', 'Bad Bunny', 'CASUAL', 'Azul', 41, 799.99, 5);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Adidas', 'Ultraboost', 'CORRIDA', 'Branco', 41, 999.99, 9);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Vans', 'Old Skool', 'SKATE', 'Preto', 41, 399.99, 10);
insert into tenis (marca, modelo, categoria, cor, tamanho, valor, estoque) values('Nike', 'Zoom Fly', 'CORRIDA', 'Rosa', 37, 499.99, 1);