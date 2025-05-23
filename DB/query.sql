-- Inserir usuários
INSERT INTO usuarios (email, senha, tipo) VALUES
('aluno@example.com', 'senha123', 'aluno'),
('professor@example.com', 'senha123', 'professor'),
('admin@example.com', 'senha123', 'admin');

-- Inserir alunos
INSERT INTO alunos (id, nome, matricula, token) VALUES
(1, 'João Augusto', '123456', 'token1'),
(2, 'Maria Silva', '654321', 'token2');

-- Inserir professores
INSERT INTO professores (id, nome, telefone, disciplina) VALUES
(1, 'Carlos Souza', '999999999', 'Matemática'),
(2, 'Fernanda Almeida', '988888888', 'Física');

-- Inserir administradores
INSERT INTO administradores (id, nome) VALUES
(1, 'Ana Costa');

-- Inserir salas
INSERT INTO salas (nome, capacidade) VALUES
('Sala 101', 40),
('Sala 102', 30);

-- Inserir reservas
INSERT INTO reservas (id_sala, usuario, data, horario) VALUES
(1, 'João Augusto', '2025-05-01', '08:00:00'),
(2, 'Carlos Souza', '2025-05-02', '10:00:00');

-- Inserir avisos
INSERT INTO avisos (titulo, descricao, data_publicacao, imagem, id_admin) VALUES
('Aviso 1', 'Aviso importante sobre a prova de amanhã.', '2025-04-28', 'imagem1.jpg', 1),
('Aviso 2', 'Mudança de horário da aula de Física.', '2025-04-28', 'imagem2.jpg', 1);

-- Inserir arquivos
INSERT INTO arquivos (nome, tipo, caminho, dados) VALUES
('Documento1', 'pdf', '/caminho/para/arquivo1.pdf', 'dados1'),
('Imagem1', 'jpg', '/caminho/para/arquivo2.jpg', 'dados2');

-- Inserir eventos no calendário
INSERT INTO calendario (titulo, descricao, data_evento, horario) VALUES
('Evento 1', 'Evento importante da escola.', '2025-05-10', '14:00:00'),
('Evento 2', 'Reunião com professores.', '2025-05-12', '10:00:00');
