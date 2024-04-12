### Diferenças entre ManagedExecutor e ExecutorService

- ManagedExecutor: especificação JAVA EE
- Executor Service: especificação JAVA SE

- A principal diferença é que o ManagedExecutor é uma versão gerenciável de um ExecutorService.

- Em um ambiente gerenciado (Java EE) não devemos usar/gerar threads threads não gerenciáveis. O não gerenciado é adequado a aplicativos Java SE.

- ManagedExecutor permite executores gerenciados que propagam o contexto do thread, bem como aqueles que não o fazem, o que pode oferecer melhor desempenho.

```
ManagedExecutor executor = ManagedExecutor.builder()
    .propagated(ThreadContext.APPLICATION)
    .cleared(ThreadContext.ALL_REMAINING)
    .build();

CompletableFuture<Integer> future = executor
    .supplyAsync(function1)
    .thenApply(function2)
    ....
```

Ref. testamos no projeto assincrono.
