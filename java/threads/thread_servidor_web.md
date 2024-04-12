## Threads em Servidor Web

- Servidor Web = threads são gerenciadas

- Especificação JSR 236

```
... as classes java.util.timer, java.util.thread e as api's de criação de pool de threads dos utilitários de concorrência do Java no pacote java.util.concurrent nunca devem ser usadas em ambientes gerenciados, pois criam threads desconhecidas pelo container.
```

- JAR simples é considerado o melhor caminho para executar tarefas assíncronas/batch. Ambiente mais preparado, onde o dev tem mais controle.

  - dificuldades: - como iniciar ?

- Servidor WEB: bom para tratar N requisições ao mesmo tempo
