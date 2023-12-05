--Alter Table
--comando para alterar a tabela
ALTER TABLE produto 
ADD CONSTRAINT FK_produto_fornecedor FOREIGN KEY (fornecedor) REFERENCES fornecedor (codigo);
--adiciona uma chave estrangeira na tabela produto, que referencia a tabela fornecedor (coluna codigo)

--Alterando nome de coluna
ALTER TABLE produto
RENAME COLUMN precoproduto TO preco;

--crinado uma tabela para depois apagar utilizando  Alter table 
CREATE TABLE teste (
    codigo char (3),
    nome char (30)
);

--apagando a tabela teste
DROP TABLE teste;
--agora veremos como limpar a tabela teste utilizando o comando truncate
INSERT into teste values ('001', 'teste1');
insert into teste values ('002', 'teste2');
insert into teste values ('003', 'teste3');

TRUNCATE TABLE teste;

--ALTERANDO NOME DE TABELA RENAME

ALTER TABLE teste RENAME to testeRename;


--deletando coluna
ALTER TABLE tablename DROP COLUMN columnname;

--rename coluna
ALTER TABLE tablename RENAME COLUMN columnname1 TO columnname2;

-- Alterar o tipo e tamanho da coluna
ALTER TABLE minha_tabela
ALTER COLUMN minha_coluna TYPE VARCHAR(255);
