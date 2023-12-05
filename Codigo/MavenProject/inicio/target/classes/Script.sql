CREATE TABLE categoria (
    id serial PRIMARY KEY,
    nome VARCHAR(45) NOT NULL
);


CREATE TABLE games (
    appid serial PRIMARY KEY,
    json text,
    categoria integer,
    CONSTRAINT fk_games_categoria FOREIGN KEY (categoria) REFERENCES categoria(id)
);

CREATE TABLE usuario (
    ID serial PRIMARY KEY,
    Nome VARCHAR(46) NOT NULL,
    Senha VARCHAR(20) NOT NULL,
    Email VARCHAR(46) NOT NULL,
    Foto BYTEA NOT NULL,
    DataNasc DATE NOT NULL,
    DataCadastro DATE NOT NULL
);

CREATE TABLE forum (
    id serial PRIMARY KEY,
    nome varchar(45) NOT NULL,
    jogos_appid int NOT NULL,
    CONSTRAINT fk_forum_jogosAppid FOREIGN KEY (jogos_appid) REFERENCES games (appid)
);

CREATE TABLE Posts (
    id serial PRIMARY KEY,
    Postagem TEXT NOT NULL,
    Forum_ID INT NOT NULL,
    User_ID INT NOT NULL,
    categoria_ID INT NOT NULL,
    CONSTRAINT fk_Post_ForumID FOREIGN KEY (Forum_ID) REFERENCES forum (id),
    CONSTRAINT fk_Post_UserID FOREIGN KEY (User_ID) REFERENCES usuario (id),
    CONSTRAINT fk_Post_CategoriaID FOREIGN KEY (categoria_ID) REFERENCES categoria (id)
);

CREATE TABLE comentario (
    id serial PRIMARY KEY,
    coment TEXT NOT NULL,
    DataPostagem TIMESTAMPTZ,
    PostID INT NOT NULL,
    UserID INT NOT NULL,
    CONSTRAINT fk_Coment_postId FOREIGN KEY (PostID) REFERENCES Posts(id),
    CONSTRAINT fk_Coment_userId FOREIGN KEY (UserID) REFERENCES usuario (ID)
);


CREATE TABLE subcoment (
    id serial PRIMARY KEY,
    conteudo text NOT NULL,
    comentID INT NOT NULL,
    CONSTRAINT fk_subcoment_ComentID FOREIGN KEY (comentID) REFERENCES comentario(id)
);
