INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Maria Oliveira', 'maria.oliveira@gmail.com', 'mariinha', '1995-05-15', 'https://cajamar.sp.gov.br/noticias/wp-content/uploads/sites/2/2021/07/site-vacinacao-33-anos.png','$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Carlos Santos', 'carlos.santos@yahoo.com', 'carlinhos', '1987-03-10', 'https://cajamar.sp.gov.br/noticias/wp-content/uploads/sites/2/2021/08/site-vacinacao-26-anos.png', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Ana Maria Costa', 'anamaria.costa@hotmail.com', 'aninha', '2000-08-22', 'https://brasil.emeritus.org/wp-content/uploads/2020/01/gesta%CC%83o-de-pessoas-.jpg', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Pedro Almeida', 'pedro.almeida@gmail.com', 'pedrinho', '1993-11-02', 'https://conteudo.imguol.com.br/c/entretenimento/38/2022/05/17/jovem-mulher-com-sindrome-de-down-1652808572452_v2_900x506.jpg', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Juliana Mendes', 'juliana.mendes@yahoo.com.br', 'juju', '1999-07-29', 'https://cajamar.sp.gov.br/noticias/wp-content/uploads/sites/2/2021/07/site-vacinacao-38-anos-2.png', 's$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

INSERT INTO usuario (nome_completo, email, apelido, data_nascimento, imagem_perfil, senha, ativo)
VALUES ('Rafael Silva', 'rafael.silva@hotmail.com', 'rafinha', '1989-02-17', 'https://lucianamacedo.com.br/wp-content/uploads/2020/10/porque-as-pessoas-buscam-procedimentos-esteticos.jpg', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);



insert into permissao (funcao, usuario_id) values ('ADMIN', 1);
insert into permissao (funcao, usuario_id) values ('USUARIO', 1);
insert into permissao (funcao, usuario_id) values ('USUARIO', 2);


INSERT INTO amigos (id_usuario, id_amigo, status) VALUES
(1, 2, 'ACEITO'),
(1, 3, 'ACEITO'),
(2, 1, 'ACEITO'),
(2, 3, 'SOLICITADO'),
(3, 1, 'ACEITO'),
(3, 2, 'SOLICITADO');

INSERT INTO post (visualizacao, conteudo_imagem, conteudo_escrito, data_inclusao, usuario_id) VALUES
('PUBLICO', 'https://exemplo.com/imagem1.jpg', 'Conteúdo do post 1', CURRENT_TIMESTAMP, 1),
('PRIVADO', null, 'Conteúdo do post 2', CURRENT_TIMESTAMP, 2),
('PUBLICO', null, 'Conteúdo do post 3', CURRENT_TIMESTAMP, 1);

INSERT INTO curtidas (id_post, id_usuario) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);

INSERT INTO comentarios (id_post, id_usuario, comentario) VALUES
(1, 2, 'Excelente post! Gostei muito do conteúdo.');

INSERT INTO comentarios (id_post, id_usuario, comentario) VALUES
(3, 1, 'Muito interessante o que você compartilhou, obrigado por compartilhar!');

INSERT INTO comentarios (id_post, id_usuario, comentario) VALUES
(2, 3, 'Gostei bastante do seu post! Continue compartilhando suas ideias.');



