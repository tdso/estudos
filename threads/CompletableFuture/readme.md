### CompletableFuture

- substitui o Future - a interface Future representa o resultado da computação assíncrona - processo de entrega de um resultado que será computado no futuro;
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

- thenApply - pode rodar métodos de forma sincrono ou assincrona
- thenCompose - pode rodar métodos de forma sincrono ou assincrona

### synchronized (apagar)

Usamos para sincronizar o acesso ao objeto. As threads executam o bloco synchronized bloqueando o objeto, de forma que somente 1 thread por vez acesse o objeto.
Podemos usar o synchronized tanto para um método quanto para um bloco de código dentro de um método.
Operação atômica = não pode ser interrompida pela metade.
Todo o bloco syncronized será executado de uma vez só, a thread que executa pode até ser pausada, para outra thread fazer algo, mas nenhuma outra thread pode entrar no bloco synchronized.
