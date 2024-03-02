### SQL

- Schema para agrupar as tabelas

```
create schema XXXXXX;
```

- criar tabelas

```
create table XXXXXX (
    nome_capo type [varchar, decimal, int] [options]
    id      int not null,
    nome    varchar(100) not null,
    preco   decimal(5,2),  // tamanho campo 5 bytes = 3 inteiros e 3 decimais
    primary key (id),
);
```

- inserir dados

```
insert into NOME_TBL values (1, 'asasa', 1.99); ou
insert into NOME_TBL (col1, col2, col3) values (val1, val2, val3);
```

- condições lógicas > ex: todos os autores com exceção do autor x e y:

  - autor = x and autor = y
  - exclui = not (autor = x or autor = y)

- definir a relação entre as tabelas

```
alter tabela xxxxx
    add constraint apelido_restricao
    foreign key (campo_chave_estrangeira)
    references nome_tabela_que_possui_chave(nome_campo_na_tabela_associada)
    on delete no action     // opcional
    on update no action     // opcional
```

    - para cada chave estrangeira gerar uma constraint

- quando precisar dar carga nas tabelas, podemos desativar o relacionamento entre elas:
  ```
  set foreign_key_checks = 0; // desativa - para ativar set para 1
  ```
- Hierarquia dos comandos

```
SELECT cd_cta, count(1)
WHERE ...
GROUP BY cd_cta
HAVING count(1) > 2
ORDER BY ...
```

ORDEM EXECUÇÃO:

- WHERE - aplica-se a linhas
- GROUP - as linhas serão agrupadas por uma das colunas da clásula do SELECT
- HAVING - aplica-se aos grupos (GROUP) - especifica condições para os campos que foram agrupados
- ORDER - ordenará o conjunto de dados montado acima

Exemplo:
SELECT \* FROM a INNER JOIN b ON (a.cod_cli = b.cod_cli and ...)

ps: full outer também chamado de alter join

- Query com parte do nome - operador %

```
...where  cidade like '%landia' or cidade like '%ma%'
```

- Delete e Update

```
delete from tabela where FIELD = CONDITION
update tabela set field = new_value where FIELD = CONDITION
```

- Juntando Tabelas

```
select livros.nome_livro, vendas.qt_vendida
from livros, vendas
where vendas.id_livro = livros.id_livro;

ou
select a.nome_livro, b.qt_vendida
from livros a, vendas b
where b.id_livro = a.id_livro;
```

- INNER JOIN

```
select a.nome_livro, b.qt_vendida
from livros a inner join vendas b
on a.id_livro = b.id_livro;
```

- LEFT JOIN = lista todos os registros da tabela da esquerda da relação
  - ex: verificar quais livros não foram vendidos

```
select livros.nome_livro, vendas.qt_vendida
from livros left join vendas
on livros.id_livro = vendas.id_livro;

no exemplo acima listaremos todos os livros da tabela a esquerda da relação, livros, e os livros que não foram vendidos aparecerão com quantidade = null - ou seja, não foram vendidos.
```

    - melhorando a query, aplicando uma condição where (filtro) dentro do join
    ```
    select livros.nome_livro, vendas.qt_vendida
    from livros left join vendas
    on livros.id_livro = vendas.id_livro
    where vendas.qt_vendida is null;

    primeiro a clausula join, depois a clausula where !!
    ```

- Funções de Agregação - max, min, count, sum, avg

  ```
  select max(qt_vendida) from vendas;

  select max(qt_vendida) from vendas group by (id_vendedor);

  group by > define o grupo de registros. A função de agregação será realizada dentro do grupo.
  ```
