-- Criando o banco de dados e selecionando ele
CREATE DATABASE FRT
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;
USE FRT;

-- Tabela de usuários (generalização para alunos, professores e administradores)
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(191) UNIQUE NOT NULL,  -- Reduzido para 191 para evitar erro no índice
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL
);

CREATE TABLE alunos (
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    matricula VARCHAR(50),  -- Remove o UNIQUE aqui
    token VARCHAR(255),     -- Remove o UNIQUE aqui
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE,
    UNIQUE (matricula(50)),  -- Índice com os primeiros 50 caracteres de matricula
    UNIQUE (token(191))      -- Índice com os primeiros 191 caracteres de token
);

-- Tabela de professores (herda de usuários)
CREATE TABLE professores (
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    disciplina VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabela de administradores (herda de usuários)
CREATE TABLE administradores (
    id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabela de salas
CREATE TABLE salas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL
);

-- Tabela de reservas (associação entre professores e salas)
CREATE TABLE reservas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_sala INT NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    data VARCHAR(50) NOT NULL,
    horario VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_sala) REFERENCES salas(id) ON DELETE CASCADE
);

CREATE TABLE avisos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_publicacao VARCHAR(255) NOT NULL,
    imagem VARCHAR(255),
    id_admin INT NOT NULL,
    FOREIGN KEY (id_admin) REFERENCES administradores(id) ON DELETE CASCADE
);

-- Tabela de arquivos (agregação entre administradores e arquivos)
CREATE TABLE arquivos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    caminho VARCHAR(50) NOT NULL,
    dados VARCHAR(255) NOT NULL
);

-- Tabela de calendário (tabela independente)
CREATE TABLE calendario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_evento VARCHAR(255) NOT NULL,
    horario VARCHAR(50) NOT NULL
);
