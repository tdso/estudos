**Resumo de Threads**

- a classe para rodar como Thread deve extends Thread ou implements Runnable

- ambas são do tipo Thread e o construtor de **Thread** recebe um tipo **Runnable**

- quando queremos que a thread chamadora espere a execução da thread chamada, usamos o método **join** () da thread chamadora, assim a thread chamadora interrompe a execução e aguarda o término da thread chamada. Para que a thread chamada não fique indefinidamente rodando, podemos passar o tempo de espera como argumento para o método **join** (tempo).

- Decorrido o tempo informando em ms a thread chamadora segue com a execução, e lembre-se que a outra continua executando, não esqueça de finalizá-la.

**Execução de 2 Threads compartilhando a mesma instância da classe**

- lembre-se: cada Thread tem seu espaço de memória e a variável local encontra-se nesse espaço onde somente a própria Thread pode acessar. A Thread faz uma cópia das variáveis locais para seu espaço de memória.

- uma primeira execução com uma variável local da instância usada no loop
- nesse cenário a execução apresentou resultados inconsistentes

- segunda execução usando uma variável de instância da classe, usada no loop.
- quando temos uma variável de instância, esta fica armazenada na heap onde todas as Threads podem acessar, isto é, neste espaço de memória a variável é compartilhada por entre as Threads.

**Sincronizando o acesso**

- acesso a variáveis na memória heap deve ser sincronizado, isto é, uma thread por vez. Podemos fazer isso através de um método sincronizado ou um bloco de código sincronizado.

- via método: use a key-word **synchronized** antes do nome do método para indicar que apenas uma thread por vez pode acessar/executar o método.

- via bloco instrução: onde uma thread por vez pode executar o bloco delimitado pelo synchronized, mas atenção: não use variáveis locais para argumento de synchronized já que elas não são compartilhadas (são locais), use apenas variáveis de instâncias (variáveis que serão disputadas pelas threads, pelo mesmo bloqueio), e/ou objetos a referência a própria instância usando **this**.

**Recurso wait() e notifyAll()**

- quando a thread chama o **wait** () suspende a execução e libera qualquer bloqueio que esteja segurando até que outro thread emita uma notificação de que algo importante aconteceu.

- lembre-se que em algum ponto devemos chamar **notifyAll** () para avisarmos que o estado mudou.

- Na maior parte das vezes chamamos o **wait** () em um loop que está testando uma condição que pode mudar. Ao chamar o **wait** () damos chance de outra thread verificar se a condição mudou e ela avisará sobre isso com o método **notifyAll()**.

- Devemos garantir que no retorno do processamento, após a chamada ao método wait() , voltemos ao início do loop para verificarmos a condição que estamos interessados e caso a condição não tenha mudado, chamar o wait(), para verificar se a outra thread irá alterar a condição.

**Revisão**

- Para ter todas as classes dentro do mesmo arquivo, para fins de teste, a classe Main não deve ser static, e o seu método main irá chamar os métodos das outras classes.

- Exemplo interessante: ao verificar o buffer (lista de mensagens) o fato de o buffer estar vazio não significa que o processamento findou, apenas que o produtor não enviou mensagens, por isso foi implementado uma mensagem de fim de sessão (fim de mensagens) para encerrar o programa que fica ouvindo a fila.

- Classe **ReentrantLock** : essa classe permite invocar os métodos **lock** () e **unlock** () e assim bloquearmos os pontos onde temos concorrência. **Atenção** : podemos ter mais de um ponto onde temos que chamar o **unlock** ().

- **Atenção** : não é boa prática termos esses métodos: **lock** () e **unlock** () espalhados pelo código.

- **TRY - FINNALY** : lembre a cláusula **finnaly** sempre será executada independente da exceção que for lançada.

- **ThreadPool** \> as implementações para ThreadPool são usadas para gerenciar threads - não temos que criar e iniciar as threads. Além disso podemos **agendar** as threads.
- **ExecutorService \>** interface que gerencia o ciclo de vida da thread.Usa o ThreadPool. Possui 3 métodos essenciais: **execute(), shutdown() e submit()**.
```
ExecutorInterface es = Executors.newFixedThreadPool(x);

es.execute(Thread ou Runnable);

es.shutdown();
```
- Caso necessite retornar um valor a partir de uma Thread:
  - a thread retorna um tipo **Future** e deve ser iniciada através do método **submit** ().
-