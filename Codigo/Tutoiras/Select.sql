--comando select serve para selecionar,consultar e exibir dados de uma tabela
select  codigo
from fornecedores
--retornou o codigo de fornecedores

--para outra tabela, podemos utilizar
select  nome,preco,descricao 
--altera a ordem de exibiçao dos dados da tabela pela ordem que foi escrita

select nome from fornecedor where nome = 'fornecedor';
--retorna o nome do fornecedor que tem o nome fornecedor, where atua como restrição de exibição     

update fornecedor set nome ='fornecedor2' where codigo = '006' or codigo ='007';
--altera o nome do fornecedor de codigo 006 e 007 para fornecedor2 com operador or, também poderia ser usado o and

select nome from fornecedor where nome = 'fornecedor2' or nome = 'fornecedor';
--retorna o nome do fornecedor que tem o nome fornecedor2 ou fornecedor, where atua como restrição de exibição



select nome,codigo
from fornecedor
where nome = 'fornecedor' or nome = 'fornecedor2'
order by codigo;
--reotorna o nome e codigo de fornecedor com restriçao do nome ser fornecedor ou forneceor2 e ordena por codigo

