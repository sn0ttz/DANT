-- veremos os comandos insert delete e update

--populando a table inserindo dados

INSERT INTO fornecedor values (
'001',
'fornecedor1'
)
INSERT INTO fornecedor(codigo,nome) values('002','fornecedor2');
INSERT INTO fornecedor(codigo,nome) values('003','fornecedor3');
INSERT INTO fornecedor(nome, codigo) values('forncedor4','004'); --pode alterar a ordem dos campos
--pode se omitir declarações de alugns valores, a tabela deve ter um valor default para primary key
INSERT INTO fornecedor(codigo) values('005');
--podemos inserir de uma vez também
INSERT INTO fornecedor(codigo,nome) values
('006','fornecedor6'),
('007','fornecedor7'),
('008','fornecedor8'),
('009','fornecedor9'),
('010','fornecedor10');


--alterar dados na tabela
update forncedor set nome ='fornecedor 1 updatado' where codigo = '001';


--deletar dados na tabela
delete from fornecedor where codigo = '001';

--cuidado ao usar esse comando sem where, ele deleta todos os dados da tabela
--pode se usar ao invés de delete from fornecedor, truncate table fornecedor para deletar todos os dados

--truncate é mais rápido que delete

