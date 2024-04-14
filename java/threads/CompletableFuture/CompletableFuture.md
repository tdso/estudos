### CompletableFuture

- substitui o Future - a interface Future representa o resultado da computação assíncrona - processo de entrega de um resultado que será computado no futuro;
  - o objetivo é não bloquear a thread principal
  - o Future e o Callable precisam de um Executor
  - método get() - bloqueante para obter o resultado
- evolução Future - mais poderoso
- suporta lambdas
- métodos não bloqueantes
- mônada = faz encadeamento de métodos e composição
- método join() para obter o resultado;
- não usa o Executor;

```
  List<Double> lista = new ArrayList<>();
  lista = dados.stream()
          .map(s -> CompletableFuture.sypplyAsync(()-> método executado de forma assincrona))
          .collect(Collectors.toList());
  List<Double> prices = lista.stream()
                          .map(CompletableFuture:join)
                          .collect(Collectors.toList());
```

Métodos:

- thenApply - execução na mesma thread um após o outro - passa o valor para frente

- thenApplyAsync - executará o processo em outra thread mas continua sendo um após o outro

- thenCompose - pode rodar métodos de forma sincrono ou assincrona

- thenCombine (outroCF(), (a,b) -> a + b)

  - quando as 2 operações terminarem combine o resultado de ambas - permite usar o timeout (sempre USE)

- thenAccept - realiza algo quando finalizar

- allOf
  var fut1 = CompletableFuture.supplyAsync(task);
  var fut2 = CompletableFuture.supplyAsync(task);
  CompletableFuture.allOf(fut1, fut2)).join()

- exceptionally (throwable -> handle (throwable))
  - sempre que chamarmos um evento assíncrono temos que ter um tratamento de exceção / erro
    - pode ocorrer um live lock - está aguardando um evento que nunca ocorre
    - método completeOnTimeout(500, 3000) - define uma janela de execução de 3s
      - se não encerrar vai retornar o valor de 500 para o pipeline
