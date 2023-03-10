DROP TABLE IF EXISTS permissao CASCADE;
DROP TABLE IF EXISTS post CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS amigos CASCADE;
DROP TABLE IF EXISTS curtida CASCADE;
DROP TABLE IF EXISTS comentario CASCADE;


CREATE TABLE usuario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    apelido VARCHAR(50),
    data_nascimento DATE ,
    imagem_perfil VARCHAR(512),
	senha VARCHAR(100) NOT NULL,
	ativo BOOLEAN NOT NULL
);
ALTER TABLE usuario ADD CONSTRAINT pk_usuario PRIMARY KEY (id);
ALTER TABLE usuario ADD CONSTRAINT uk_usuario_email UNIQUE (email);


CREATE TABLE amigos (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  id_usuario BIGINT NOT NULL,
  id_amigo BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL
);

ALTER TABLE amigos ADD CONSTRAINT fk_amigos_usuario FOREIGN KEY (id_usuario) REFERENCES usuario;
ALTER TABLE amigos ADD CONSTRAINT fk_usuario_amigos FOREIGN KEY (id_amigo) REFERENCES usuario;
ALTER TABLE amigos ADD CONSTRAINT status_check CHECK (status IN ('SOLICITADO','ACEITO'));


CREATE TABLE permissao (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	funcao VARCHAR(100) NOT NULL,
	usuario_id BIGINT NOT NULL
);
ALTER TABLE permissao ADD CONSTRAINT pk_permissao PRIMARY KEY (id);
ALTER TABLE permissao ADD CONSTRAINT uk_permissao UNIQUE (funcao, usuario_id);
ALTER TABLE permissao ADD CONSTRAINT fk_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;


CREATE TABLE post (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	visualizacao VARCHAR(50) NOT NULL,
	conteudo_imagem VARCHAR(255),
	conteudo_escrito VARCHAR(255),
	data_inclusao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	usuario_id BIGINT
);
ALTER TABLE post ADD CONSTRAINT pk_post PRIMARY KEY (id);
ALTER TABLE post ADD CONSTRAINT fk_post_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;
ALTER TABLE post ADD CONSTRAINT visualizacao_check CHECK (visualizacao IN ('PUBLICO','PRIVADO'));



CREATE TABLE curtida(
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_post BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL
);
ALTER TABLE curtida ADD CONSTRAINT fk_post_usuario FOREIGN KEY (id_usuario) REFERENCES usuario;
ALTER TABLE curtida ADD CONSTRAINT fk_post_curtidas FOREIGN KEY (id_post) REFERENCES post;
ALTER TABLE curtida ADD CONSTRAINT unq_curtida_usuario_post UNIQUE (id_usuario, id_post);



CREATE TABLE comentario(
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    id_post BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    comentario VARCHAR(255) NOT NULL
);
ALTER TABLE comentario ADD CONSTRAINT fk_post_usuario FOREIGN KEY (id_usuario) REFERENCES usuario;
ALTER TABLE comentario ADD CONSTRAINT fk_post_comentario FOREIGN KEY (id_post) REFERENCES post;
