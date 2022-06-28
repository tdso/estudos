# Notas Leitura Threads

Processos (arquivos executáveis) executam concorrentemente no SO. Threads rodam concorrentemente em um mesmo processo.

Thread representa uma instância da CPU da máquina virtual java, e que tem associado um trecho de código que será executado e uma área de memória.

O uso de threads é recomendado quando executamos pelo menos duas coisas ao mesmo tempo em um programa para aproveitar as múltiplas CPU's ou para evitar que o programa inteiro fique travado ao executar uma operação demorada.

A JVM faz um mapeamento das threads java para as threads nativas do SO. A JVM funciona como uma abstração em cima do SO.
![imagem](threads_1.png)

### synchronized

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

### Esperando uma thread

A thread A deve esperar pela execução da thread B > a thread A invoca o método wait(), a thread B em algum ponto deve notificar que finalizou, e então invocar o método notify(). Os métodos wait e notify estão na classe Object. Se o método wait for chamado fora de um bloco synchronized uma exceção será lançada em tempo de execução.

Porque quando usamos o synchronized a JVM pega o chave (ID) do objeto e faz um lock para garantir um único acesso por thread. O wait() deixa a thread esperando e devolve a chave para a outra thread para que ela realize a sua tarefa, quando finaliza, chama o notify() que devolve a chave para a thread que estava em wait. A notificação somente pode ser feita quando estamos com a chave do objeto em mãos, isto é, dentro de um bloco synchronized.

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

- jconsole - ferramenta para exibir as threads em execução e o consumo de recursos

### Criando Threads

Podemos criar através da classe Thread, passando uma tarefa que implementa a interface Runnable. Mas abrir, fechar threads é custoso, não há uma forma de reaproveitar as threads ? Sim ... temos a classe Executors e que possui 2 métodos:

- newFixedThreadPool (integer) - integer representa o número fixo de threads
- newCachedThreadPool() - a JVM vai administrar a criação e a destruição das threads

Os métodos acima retornam um ExecutorService que possui o método execute() para chamar uma tarefa que implementa a interface Runnable e Callable (como verá mais adiante).

### Threads com Retorno

Interface Callable : permite devolver valor, e podemos definir um tempo limite de execução para a thread. Executada somente através de um pool (ExecutorService).
Para executar:

- execute (interface Runnable)
- submit (interface Callable)

Callable retorna um objeto Future e para obter o resultado basta chamar o método get().
Porém o método get() é bloqueante então caso não deseje bloquear a thread, recomenda-se que se crie outra thread para obter o resultado.
