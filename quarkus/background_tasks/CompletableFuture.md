### Comparativo uso ManagedExecutor x CompletableFuture

## ManagedExecutor

- quando testamos chamando o método submit() a thread que foi criada é do grupo executor-thread-x;

## CompletableFuture

- usando CompletableFuture e acionando o método supplyAsync a tarefa foi submitida em uma thread de um grupo diferente a do ForkJoinPool.commonPool-worker-xx;

Cada implementação lida com um pool de threads.

As threads do `ForkJoinPool` e do `executor-thread` no Quarkus têm diferenças significativas em termos de como elas lidam com tarefas e sub-tarefas:

1. **ForkJoinPool**: Este é um framework que foi projetado para resolver problemas que podem ser divididos em tarefas menores de maneira recursiva (também conhecido como "dividir para conquistar"). Ele é especialmente eficiente quando a maioria das tarefas gera outras sub-tarefas¹. O `ForkJoinPool` usa uma técnica chamada "work-stealing", onde todas as threads no pool tentam encontrar e executar sub-tarefas criadas por outras tarefas ativas⁵. Isso permite um processamento eficiente, evitando que threads fiquem ociosas esperando por outras threads para entregar resultados (ex. java streams).

2. **ExecutorService (executor-thread)**: Este é um serviço que gerencia um pool de threads e é geralmente usado para processar muitas solicitações independentes de maneira concorrente. Ele tem uma única fila, então, se você tem 20 tarefas e 5 threads, cada uma dessas threads processará essas tarefas.

Em resumo, o `ForkJoinPool` é mais eficiente para problemas que podem ser divididos em sub-tarefas menores e que podem ser resolvidos de forma independente, enquanto o `ExecutorService` é mais adequado para processar muitas solicitações independentes de maneira concorrente. No entanto, ambos podem ser usados para paralelizar tarefas, dependendo das características específicas do problema que você está tentando resolver.

## Processamento Assíncrono Quarkus

- Anotamos o retorno do método de uma operação REST com o tipo Uni (ou CompletableFuture) e o próprio Quarkus já usou uma thread de loop de eventos (I/O) não bloqueante conforme exibido no console: vert.x-event-loop-thread-0

- Agora se usarmos a anotação @Blocking informamos ao Quarkus que o código é bloqueante, e a thread usada agora é outra executor-thread-x (a mesma do ManagedExecutor - são as threads de bloqueio)

- Vertx Event Loop: modelo de concorrência baseado em um loop de eventos onde todas as operações de I/O e manipulação de eventos são executadas em uma única thread, por isso não podemos bloqueá-la.
