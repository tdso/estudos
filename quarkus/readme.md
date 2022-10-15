# Notas Quarkus - Redhat

## Arquitetura Microserviços

- Método de divisão de domínios da lógica de negócios em processos independentes, que se comunicam com outros processos pela rede. Cada serviço gira em torno de um domínio de negócio específico.
- Microserviços gerenciados independente, implantados usando automação.
- adequados para ambiente cloud
- dimensionados mais facilmente

Desafios:

- rastreamento (trace)
- monitoramento (N servidores) - arquivos de log's em vários locais
- proteger os aplicativos

Nota:

- microserviços deve ser uma unidade de implantação autocontida que pode ser implantada e usada com independência de outros serviços.
- os microserviços não tem estado.
- são empacotados e fornecidos por containers

Design para Falhas

- devem ser projetados para se recuperarem. Lidam com falhas e a experiência do usuário final não é afetada por uma única falha no serviço.

  - alta disponibilidade
  - usar padrão disjuntor para não propagar a falha
  - usar padrão bulkhead para evitar a sobrecarga de um serviço individual e para isolar falhas de propagação em todo o sistema, limitando o acesso simultâneo a serviços dependentes.
  - monitoramente em tempo real

  Padrão Disjuntor

  - Os microserviços devem lidar normalmente com falhas de downstream de serviços aos quais ele depende.
  - O objeto disjuntor envolve as chamadas de função para os serviços dependentes e monitora o sucesso das chamadas.

  - Quando tudo está normal, chamadas bem sucedidas, o disjuntor estará no estado fechado. Quando o número de falhas (uma exceção, tempo limite) atinge um valor pré-determinado o disjuntor falha - quando o disjuntor está aberto, nenhuma chamada é feita para o serviço dependente, mas uma resposta de fallback é retornada. Após um período de tempo configurável, o disjuntor passa para semiaberto. Nesse estado, o disjuntor executa as chamadas de serviço periodicamente para verificar a integridade do serviço dependente. Se o serviço estiver integro novamente, e as chamadas de teste forem bem sucedidas, o estado do circuito volta a ser fechado.

  Padrão Bulkhead

  - Isolar as dependências umas das outras e limitar o número de threads simultâneas que tentam acessar cada uma delas. Esse isolamento significa que essa chamada está impedida de usar mais do que essas threads.
  - Quando um aplicativo faz uma solicitação de conexão com um componente atrás do bulkhead, este verifica a disponibilidade de conexão com o componente solicitado, se o número de conexão estiver abaixo do limite, o bulkhead aloca a conexão, quando não são permitidas mais conexões simultâneas, o bulkead aguarda um intervalo de tempo definido, se nenhuma conexão ficar disponível dentro do período, o bulkhead rejeita a chamada.
  - Exs implementações: microprofile fault tolerance, hystrix, mesh, camel EIP, Disjuntor Apache Commons

### Microprofile

- Especificação Microprofile define um plataforma que otimiza o java para uma arquitetura baseada em microserviços e fornece portabilidade em vários tempos de execução.

- Microprofile não é uma especificação completa como o JSR. Versão inicial: JAX-RS, CDI, JSON-P.

## Quarkus

- Estrutura nativa em nuvem > plataforma de implantação > container
- Inicialização rápida
- Pegada de memória pequena
- Menor uso em disco
- Programação imperativa e reativa
- Suporte compilação antes do tempo AOT - ahead-of-time do GraalVM (JVM especial)
  - JVM precisa saber no momento da compilação quais classes serão usadas, para ofertar suporte executáveis nativos.
  - durante a compilação o GraalVM remove todas as classes desnecessárias, bibliotecas dependentes e da JVM.

### Implementação Microserviços

- Beans é qualquer objeto gerenciado pelo CDI - um bean especifica o tipo e a semântica dos outros beans dos quais depende.
- Ex de anotações que definem um bean: @ApplicationScoped, @SessionScoped, @ RequestScoped

### DI no Quarkus

- implementa apenas um subconjunto de especificação CDI
- ignora descritores no arquivo beans.xml
- implementado pelo Quarkus.Arc

### Revisões da Especificação

- JAX-RS - criação serviços web padrão rest, uso de anotação no nível de classe ou método
- JSON-b - manipular json - camada de ligação da estrutura do json para objetos java
- Para essa ligação funcionar as classes "pojo" devem ter:
  - construtores pojo sem parâmetros ou
  - anotar o construtor com @JsonbCreator e os nomes de parâmetros devem corresponder aos nomes das propriedades.
  - os atributos devem ser públicos 
  - existir métodos get/set para cada atributo
> quando não quiser deserializar um atributo > @JsonbTransient

> se quiser ignorar a propriedade para serialização use o @JsonbTransient no método get

### Persistência no Quarkus

- Dependências necessárias para persistência:
  - quarkus-hibernate-orm
  - quarkus-hibernate-orm-panache
  - driver do BD: h2 / postgres ... etc
- Todas as configurações em um único arquivo: application.properties - ex:
  - quarkus.datasource.db-kind=
  - quarkus.datasource.username=
  - quarkus.datasource.password=
  - quarkus.datasource.jdbc.url=
  - quarkus.hibernate-orm.database.generation=drop-and-create

#### Implementações

- EntityManager = tem que injetá-lo
- Panache com Repository - objetivo separar a lógica de recuperação de dados e seu mapeamento para entidades.
  - deve-se criar um repositório que extende de PanacheRepository para a entidade e injetá-lo onde for preciso - ex:
  ```
  @ApplicationScoped
  public class ExpenseRepository implements PanacheRepository<Expense { ... }
  ```

- Panache com Active Record - define os métodos que acessam a entidade na própria classe da entidade
- basta extender PanacheEntity 
  - os atributos da entidade devem ser públicos 
  - os atributos devem ser acessados com métodos get/set
  - possível criar métodos estáticos personalizados que executam consultas especifícas

> pasta: src/main/resources = pode-se criar o arquivo import.sql com instruções sql para popular ou criar o banco de dados

  ** ex pag 57 e 68



---

