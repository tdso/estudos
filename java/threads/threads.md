# Notas Leitura Threads

Processos (arquivos executáveis) executam concorrentemente no SO. Threads rodam concorrentemente em um mesmo processo.

Paralelismo: tem a ver com a quantidade de processos sendo executado ao mesmo tempo.
Concorrência: quando temos mais de um trecho de código executando concorrendo pelo mesmo recurso, ex um arquivo.

Thread representa uma instância da CPU da máquina virtual java, e que tem associado um trecho de código que será executado e uma área de memória.

O uso de threads é recomendado quando executamos pelo menos duas coisas ao mesmo tempo em um programa para aproveitar as múltiplas CPU's ou para evitar que o programa inteiro fique travado ao executar uma operação demorada.

A JVM faz um mapeamento das threads java para as threads nativas do SO. A JVM funciona como uma abstração em cima do SO.
![imagem](threads_1.png)

Revisão:

- ter uma classe que:

  - implementa Runnable;
  - atributo para guardar um resultado, se for o caso;
  - método publico para retornar o valor do atributo acima;

- ter uma instância da classe Thread que receberá uma instância da classe que implementou o Runnable;
- iniciar a execução da Thread através do método start();
- a instância da Thread que foi startada tem que se juntar (método join) a thread que a chamou. Isso fará a thread principal (chamadora) esperar o término da execução da thread, isso é um método bloqueante.
- por fim, quando o processamento retornar a Thread principal (após o método join)obtenha o resultado chamando o método público que retorna o valor do atributo que armazena a resposta.

## Tipos Thread Safe

- classes atômicas
- AtomicReference<Object>
- volatile (ler/gravar na variável de destino e não no cache local)

### synchronized

A palavra synchronized pode ser usada em um método ou em um trecho de código, para garantir que algum recurso (ex varável)
seja acessado exclusivamente por uma thread por vez. A desvantagem é a perda do paralelismo neste trecho.

Usamos para sincronizar o acesso ao objeto. As threads executam o bloco synchronized bloqueando o objeto, de forma que somente 1 thread por vez acesse o objeto.
Podemos usar o synchronized tanto para um método quanto para um bloco de código dentro de um método.
Operação atômica = não pode ser interrompida pela metade.
Todo o bloco syncronized será executado de uma vez só, a thread que executa pode até ser pausada, para outra thread fazer algo, mas nenhuma outra thread pode entrar no bloco synchronized.

### volatile / AtomicXXXXX

Quando usamos a palavra reservada volatile para um atributo significa que a thread não deve cachear o atributo marcado com volatile - cada thread deve buscá-lo diretamente da memória principal.
O acesso a um atributo volatile funciona de maneira atômica, como se fosse sincronizado.

Os tipos AtomicXXXX tem a mesma finalidade, as threads usarão as variáveis sem cache. O método get() recupera o valor, e o set() atribui o valor.

Volatile e Atomic evitam o uso do synchronized.

ps: a classe ReentrantLock permite fazermos o lock explicito, mas fica a cargo do dev a liberação do lock (unlock).

Java.util.Vector = thread-safe = lista sincronizado = o código funciona corretamente mesmo com várias threads compartilhando o objeto.

## Executores

- Executors.newFixedThreadPool (x)
  .newSingleThreadExecutor()
  .newCachedThreadPool() // não especifica a qtde a JVM gerencia as Threads

- o Executor deve ser sempre encerrado, use o método shutdown()

  - mas e se a tarefa não tiver finalizado ? método: awaitTermination(5, TimeUnit.SECONDS) // espera 5 seg se não terminar é encerrado, após a chamada desse método, chame o shutdown() ou shutdownNow().

- método execute(runnable) // chama uma tarefa q implementa runnable
- método submit(callable) // chama uma tarefa q implementa callable
- método invokeAll(lista tarefas) // executa todas as tarefas
- método invokeAny(lista) // submete todas e pega o resultado da primeiro tarefa

## Schedulers - Tarefas Agendadas

- ScheduleExecutorService = Executors.newScheduleThreadPool
  Executors.schedule(task, 9, TimeUnit.SECONDS) // agendou para 9s a frente
  Executors.scheduleAtFixedRate(task, InitialDelay, Periodo, TimeUnit.Seconds)
  // InitialDelay - qto tempo demora para executar 1 vez
  // Periodo - de quanto em quanto tempo executa novamente
  // ex: a cada 1s executa a tarefa mas e se a tarefa demorar mais de 1s para executar ?
  // assim que acabar a execução já vai iniciar a outra sem esperar 1s
  // método scheduleWithFixedDelay > sempre vai ter um intervalo de 1s entre as execuções
  // independente do tempo que a tarefa demorar

### Esperando uma thread

A thread A deve esperar pela execução da thread B > a thread A invoca o método wait(), a thread B em algum ponto deve notificar que finalizou, e então invocar o método notify(). Os métodos wait e notify estão na classe Object. Se o método wait for chamado fora de um bloco synchronized uma exceção será lançada em tempo de execução.

Porque quando usamos o synchronized a JVM pega o chave (ID) do objeto e faz um lock para garantir um único acesso por thread. O wait() deixa a thread esperando e devolve a chave para a outra thread para que ela realize a sua tarefa, quando finaliza, chama o notify() que devolve a chave para a thread que estava em wait. A notificação somente pode ser feita quando estamos com a chave do objeto em mãos, isto é, dentro de um bloco synchronized.

Nota: é na classe de negócio que definimos o que será sincronizado (synchronized) bem como onde estarão as chamadas aos métodos wait() e notify().

### Método join()

O método join() deve ser chamado pela thread que será executada para informar a thread atual, que deve ela deve aguardar a finalização da execução da nova thread.
Main
t1.join();
t1.start();
t2.start();

### Threads Daemon

São como prestadores de serviço para outras threads, usadas para dar apoio a tarefas e só são necessárias rodar quando as threads normais ainda estão sendo executadas. Uma thread daemon não impede a JVM de de terminar desde que não existam mais threads principais em execução.

Podemos setar uma thread como prestadora de serviço para outras threads, usamos o método setDaemon() e configurá-lo para true antes de iniciar a thread.

### Dica

- os métodos wait/notify estão em todos os objetos (Object) e devem ser usados nos objetos que são compartilhados entre as threads (não usar como vimos na classe que implementa runnable).

### Criando Threads

Podemos criar através da classe Thread, passando uma tarefa que implementa a interface Runnable. Mas abrir, fechar threads é custoso, não há uma forma de reaproveitar as threads ? Sim ... temos a classe Executors e que possui 2 métodos:

- newFixedThreadPool (integer) - integer representa o número fixo de threads
- newCachedThreadPool() - a JVM vai administrar a criação e a destruição das threads
- Ex:
  ExecutorService pool = Executors.newFixedThreadPool(4);
  pool.execute(task);

O ExecutorService simplifica a execução de tarefas assíncronas, já que provê um pool de threads.
Os métodos acima retornam um ExecutorService que possui o método execute() para chamar uma tarefa que implementa a interface Runnable (não retorna nada) e/ou Callable (retorna valor) (como verá mais adiante).

### Threads com Retorno

Interface Callable : permite devolver valor, e podemos definir um tempo limite de execução para a thread. Executada somente através de um pool (ExecutorService).
Para executar:

- execute (interface Runnable)
- submit (interface Callable) (pode submeter tanto Callable quanto Runnable)

Callable retorna um objeto Future e para obter o resultado basta chamar o método get().

Porém o método get() é bloqueante então caso não deseje bloquear a thread, recomenda-se que se crie outra thread para obter o resultado.

Caso o Callable não atingiu o tempo especificado e não acabou a execução, uma exception será gerada e no catch devemos chamar o método cancel() para cancelar a execução.

O ExecutorService não para sozinho ou é destruído, por iso temos que:

- chamar o método **shutdown()** que não causa a destruição imediata do ExecutorService, o shutdown vai ocorrer quando todas as tarefas que estão rodando finalizar.
- chamar o método **shutdownNow()** que destrói imediatamente o ExecutorService.

Nota: FutureTask > permite rodar um Callable sem um pool de thread. FutureTask recebe no construtor um Callable, e implementa a interface Runnable, assim FutureTask é iniciada com o método start(), e obtemos o resultado com o método get(). FutureTask é um intermediário entre o Runnable e o Callable.

### Interface Future

- método submit() e invokeAll() retornam um objeto ou uma lista do tipo Future que permite-nos pegar o resultado da execução da tarefa ou checar o status da tarefa:
  - podemos verificar se a tarefa já foi processada - isDone()
  - pegar o resultado - get() (bloqueante)
  - cancela a execução - cancel(true)

### Interface ScheduledExecutorService

- executar depois de um tempo predefinido/período - ex:

```
  ScheduledExecutorService executorService = Executors.newSingleThreadScheduleExecutor();
  // para executar depois de um tempo fixo
  Future<String> resp = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
  // para rodar após 100 ms, e depois vai rodar a mesma tarefa a cada 450 ms
  Future<String> resp = executorService.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.SECONDS);
```

- se o processador precisar de mais tempo para executar a tarefa, o schedule vai esperar a tarefa corrente finalizar antes de iniciar.

- se precisar ter um tempo fixo entre as execuções use: scheduleWithFixedDelay

### Fila com Threads

Uma fila é uma estrutura de dados onde o primeiro elemento que entra é o primeiro elemento que sai. Uma das implementações é o LinkedList que implementa tanto uma lista quanto uma fila.

- Ex. Queue\<Objeto> fila = new LinkedList<>();

O LinkedList possui métodos não bloqueantes para tratar os elementos:

- offer(); adiciona a fila
- size();
- poll(); reupera e remove da fila

Como alternativa, para uso em paralelo, temos o BlockingQueue (java.util.concurrent), que é uma estrutura thread-safe. Temos que definir o tamanho do array no BlockingQueue.

- Ex. BlockingQueue\<String> fila = new ArrayBlockingQueue<>(100);

Métodos bloqueantes:

- take(); recupera e remove o item da fila - quando não há mais itens na lista, bloqueia a execução aguardando novos itens (útil no lado do consumidor)
- put(); adiciona o elemento na fila - se estiver cheio aguarda até ter espaço na fila (útil no lado do produtor)

Em alguns casos precisaremos processar os comandos (as mensagens) na ordem em que chegarem no servidor. Por ex. o sinal da bolsa. Nesse caso temos que garantir a ordem dos elementos na fila com base em algum critério. Para isso podemos usar:

- BlockingQueue: PriorityBlockingQueue

Para usarmos o PriorityBlockingQueue o tipo do objeto da fila tem que implementar a interface Comparable (se não uma exceção é lançada). E não precisamos definir o tamanho de um tipo PriorityBlockingQueue, tem alocação dinâmica.

### ArrayBlockingQueue

Forma de trabalhar com listas seguras - elimina-se a necessidade de trabalhar com bloqueio (synchronized ou ReentrantLock).

O tipo de lista segura tem métodos especificos para trabalhar com transaction que são interrompidos caso o recurso (array) esteja bloqueado. Ex: peek() e take().

**Atenção**: como vimos no exemplo (aula 305 - Tim) a Thread pode ser interrompida entre um comando e outro, ex:
1 - verifica se a lista está vazia, não está e continua;
2 - porém logo após a verificação a execução é interrompida, e passa para outra Thread;
3 - essa segunda Thread faz a verificação e remove o item e agora a lista está vazia;
4 - a primeira Thread retoma a execução, mas a lista está vazia, ela já passou pela verificação, então tentar acessar o elemento retornará um NullPointerException.

**Solução**: sincronizar o bloco de instruções para garantir atomicidade.

**Conclusão**: Mesmo utilizando listas seguras não podemos abrir mão do synchronized.

### Tratamento de Exceção na Thread

Quando ocorre uma exceção na Thread ela deve ser tratada na thread em que ocorreu, as outras threads nem ficarão sabendo do que ocorreu.

A classe Thread possui um método - setUncaughtExceptionHandler() - no qual é possível passarmos um objeto que faça o tratamento das exceções da thread. Esse objeto deve implementar a interface UncaughtExceptionHandler.

Mas e quando tivermos um pool ? não temos a instância da thread para configurar !!
Mas o Executor tem um construtor para o pool de threads que aceita que passemos um FactoryThread, por meio dele customizamos a thread que será criada pelo pool.

### Ferramentas (pasta bin do jdk)

- jconsole
- visualvm
