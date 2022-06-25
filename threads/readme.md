# Notas Leitura Threads

A JVM faz um mapeamento das threads java para as threads nativas do SO. A JVM funciona como uma abstração em cima do SO.
![imagem](threads_1.png)

synchronized

Usamos para sincronizar o acesso ao objeto. As threads executam o bloco synchronized bloqueando o objeto, de forma que somente 1 thread por vez acesse o objeto.
Podemos usar o synchronized tanto para um método quanto para um bloco de código dentro de um método.
Operação atômica = não pode ser interrompida pela metade.
Todo o bloco syncronized será executado de uma vez só, a thread que executa pode até ser pausada, para outra thread fazer algo, mas nenhuma outra thread pode entrar no bloco synchronized.

ps: a classe ReentrantLock permite fazermos o lock explicito, mas fica a cargo do dev a liberação do lock (unlock).

Java.util.Vector = thread-safe = lista sincronizado = o código funciona corretamente mesmo com várias threads compartilhando o objeto.

Esperando uma thread

A thread A deve esperar pela execução da thread B > a thread A invoca o método wait(), a thread B em algum ponto deve notificar que finalizou, e então invocar o método notify(). Os métodos wait e notify estão na classe Object. Se o método wait for chamado fora de um bloco synchronized uma exceção será lançada em tempo de execução.

Porque quando usamos o synchronized a JVM pega o chave (ID) do objeto e faz um lock para garantir um único acesso por thread. O wait() deixa a thread esperando e devolve a chave para a outra thread para que ela realize a sua tarefa, quando finaliza, chama o notify() que devolve a chave para a thread que estava em wait. A notificação somente pode ser feita quando estamos com a chave do objeto em mãos, isto é, dentro de um bloco synchronized.

Threads Daemon

São como prestadores de serviço para outras threads, usadas para dar apoio a tarefas e só são necessárias rodar quando as threads normais ainda estão sendo executadas. Uma thread daemon não impede a JVM de de terminar desde que não existam mais threads principais em execução.

Podemos setar uma thread como prestadora de serviço para outras threads, usamos o método setDaemon() e configurá-lo para true antes de iniciar a thread.

Dica

os métodos wait/notify estão em todos os objetos (Object) e devem ser usados nos objetos que são compartilhados entre as threads (não usar como vimos na classe que implementa runnable).



