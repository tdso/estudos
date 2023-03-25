### SQL

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
