# Questão

Código reativo não bloqueia a thread I/O

Como lidamos com o bloqueio de código ?

Como integrar esse código de bloqueio e o pipeline reativo ?
Isolar esse código e executá-lo em thread de trabalho.

Projeto referência: ws-quarkus/assincrono

# Atenção

Tenha cuidado ao usar beans com @RequestScoped dentro de uma classe \uni ou Multi. Como operações reativas podem ser executadas em threads diferentes, o contexto da solicitação pode não estar ativo.

# Mutiny

Fornece dois operadores para personalizar as threads de execução:

- runSubscriptionOn() - configura a thread a ser usada no momento da assinatura
- emitOn() - configura a thread a ser usada para despachar os eventos downstream

Importante:

1 - os métodos acima podem receber como argumento o valor: Infrastructure.getDefaultWorkerPool(),
assim permite que a plataforma subjacente forneça um pool de trabalhadores padrão.

2 - runSubscriptionOn() funciona quando a operação de bloqueio acontece no momento da assinatura.

3 - caso o processamento dos itens seja bloqueante usamos emitOn()

4 - A documentação prega cuidado com esse operador, pois pode levar a problemas de simultaneidade com objetos
não thread-safe

## Diferença entre .emitOn(Infrastructure.getDefaultExecutor()) e .emitOn(Infrastructure.getDefaultWorkerPool())

Infrastructure.getDefaultExecutor():

Este método retorna um Executor padrão que é geralmente utilizado para tarefas leves e rápidas.

É mais adequado para operações que não bloqueiam, como manipulação de eventos ou callbacks rápidos.
Utilizar .emitOn(Infrastructure.getDefaultExecutor()) significa que as operações serão executadas
em um pool de threads que é otimizado para tarefas curtas e não bloqueantes.

Infrastructure.getDefaultWorkerPool():

Este método retorna um pool de threads que é mais adequado para tarefas que podem ser mais
pesadas ou que podem bloquear.

É ideal para operações que podem demorar mais tempo, como I/O intensivo ou processamento
de dados.

Utilizar .emitOn(Infrastructure.getDefaultWorkerPool()) significa que as operações
serão executadas em um pool de threads que pode lidar com tarefas mais pesadas e
potencialmente bloqueantes.

# Conceitos

Munity --> onItem() OU onFailure()

onItem() >> todos os métodos que tratam item individualmente
onFailure() >> todos os métodos que tratam falhas e como se recuper da falha

2 Classes:
Uni - representa um único item ou falha
Multi - representa N itens, ou uma falha ou um evento completed

São lazy, isso significa que se não assiná-los, não dispara a operação.

## Observando eventos

Para cada tipo de evento podemos chamar o método:

invoke() esse método não modifica o evento.

Podemos observar o evento sem alterá-lo. O restante do fluxo receberá o mesmo evento.
Útil quando precisamos implementar efeito colateral ou rastrear o código.

## Transformar os Eventos

Método transform() diferente do invoke() produz um novo item.

O transform() chama a função passada e envia o resultado para o assinante (downstream).
Esse método é bloqueante, isto é, síncrono.

Caso precise chamar uma função demorada, ou um serviço remoto, ou um acesso ao BD, essas ações
devem ser assíncronas, e para isso use:
transformToUni (item -> callRemote(item))
transformToMulti (item -> callRemote(item))

ex: Uni.onItem().transformToUni(item -> callRemote(item))
.subscribe().with(s -> sout("Status: " + s));

## Concatenação e Merge

Cenário: recuperar todos os usuários e depois todos os pedidos da cada usuário.

users.getAllusers()
.onItem().transformToMultiAndConcatenate(user -> orders.getOrder(user));

transformToMultiAndConcatenate = toma os Multis um por por um e concatena - essa
abordagem preserva a ordem mas limita a concorrência, pois os pedidos serão
recuperados para cada usuário por vez.

Caso não seja necessário preservar a ordem, use o transformToMultiAndMerge(), assim
a ação passado ao método será executado de forma concorrente.

# Recuperando das Falhas

As falhas são inevitáveis e precisamos tratá-las.

No Mutiny as falhas são eventos, podemos observá-las e tratá-las.

A cada onItem().método >> podemos incluir onFailure()

ex: onItem().transform(x -> upperCase(x))
.onFailure().retry()
.withBackOff(Duration.ofSeconds(3)) // inclui um intervalo entre as repetições
.atMost(3)

A falha é um evento terminal, isto é, novos itens não são permitidos após um evento de Failure,
a não ser que a falha seja tratada e o sistema permita como no exemplo acima.

# Combinando Itens

Combinar itens (Uni's) é muito comum quando queremos executar operações concorrentes e juntar seus resultados.

Quando precisamos combinar itens de múltiplas fontes, como executar duas operações independentes, de forma concorrente,
e depois ter ambos os resultados. Ex:

```
Uni<Produtos> uniProd = produtos.get();
Uni<Fornec> uniFornec = fornecedor.get();

Uni.combine().all.unis(uniProd, uniFornec)
    .asTuple()
    .onItem()
    .transform(tuple -> tuple.getItem1() + tuple.getItem2());
```

Quando ambas operações finalizarem os resultados serão coletados dentro de uma tupla e enviados fluxo abaixo.
