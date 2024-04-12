## Interface Funcional

- Interface que possui um único método abstrato. Suas implementações serão tratadas como expressão lambda.

- Expressão lambda > quando passamos a implementação implicitamente para o método abstrato, como só tem um método abstrato a JVM induz que a implementação é para o método.

- Exemplo de Interfaces Funcionais:

  - Predicate
  - Function
  - Consumer

- quando temos funções de uma única linha os {} são dispensáveis e o return é implícito.

### Composição de Função

Function<Integer, Predicate<Integer>> emaiorq = pivot -> number -> number > pivot;
ps. veja abaixo a definião de Function = 1 argumento tipo de entrada e 2 argumento é o tipo de saída
Stream<Integer> temp = values.stream()
.filter(emaiorq.apply(4))
.filter(outrometodo)
.map(algo);

    - quando chamammos o método apply acima passando 4, será retornado a função: number -> number > 4
    - valor de number será fornecido pelo fluxo de streams
    - uma fução retorna outra função com 1 parâmetro da função incorporado nela

### Predicate (test(T t))

- Representa um <b>Predicado</b> (função de valor boleano) de um argumento.
- Esta é uma interface funcional cujo método funcional é test(Object).

```
    public static void main(String[] args) {

        Predicate<Integer> predicado = t -> Integer.parseInt(t.toString()) % 2 == 0;
        Consumer<Integer> consumer = c -> System.out.println(c);
        Stream<Integer> listaStream = Stream.of(1, 2, 3, 4, 5);

        listaStream.filter(predicado)
                .forEach(consumer);
    }
```

### Consumer (accept(T t))

- Interface funcional com um método abstrato accept(T t) que não retorna nada, apenas executa uma ação.
- Ex. o método forEach da classe List, recebe um consumer, como no exemplo acima.

### Function (R apply(T t))

- Interface funcional que define 2 tipos : um de entrada e um de saida. Ela recebe um tipo T e retorna um R.

> Interface Function <T,R> {
> R apply (T t) ;
> }

- No exemplo abaixo a função map() do Stream recebe como argumento uma Function.

```
    public static void main(String[] args) {

        Predicate<Integer> predicado = t -> Integer.parseInt(t.toString()) % 2 == 0;

        Consumer<Integer> consumer = c -> System.out.println(c);

        Function<Integer, Integer> function = e -> Integer.parseInt(e.toString()) * 10;

        Stream<Integer> listaStream = Stream.of(1, 2, 3, 4, 5);

        listaStream.filter(predicado)
                .map(function)
                .forEach(consumer);
    }
```

- Outro exemplo:

```
    private <T,R> List<R> map (List<T> list, Function <T,R> func){

        List<R> resposta = new ArrayList<>();
        for (T e : list) {
            R r = func.apply(e)
            resposta.add(e);
        }
        return resposta;
    }
```
