# Referências Linguagem Java

## Manipulando arquivos (I/O)

- pasta arquivos - código exemplo

## Interface Funcional

- Interface que possui um único método abstrato. Suas implementações serão tratadas como expressão lambda.

- Exemplo de Interfaces Funcionais:
  - Predicate
  - Function
  - Consumer

### Predicate

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

### Consumer

- Interface funcional com um método abstrato accept(T t) que não retorna nada, apenas executa uma ação.
- Ex. o método forEach da classe List, recebe um consumer, como no exemplo acima.

### Function

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

## Stream

- uma sequência de elementos advinda de uma fonte de dados que oferece suporte a operações agregadas". Fonte de dados: coleção, array, função de iteração, recurso
  de E/S.
- Solução para processar sequências de dados de forma:

  - paralela - imutável e thread-safe
  - sem efeitos colaterais
  - sob demanda (lazy evaluation)
  - pipeline - operações em streams retornam novas streams. Torna possível criar uma cadeia de operações (fluxo de processamento).

- O pipeline é composto por zero ou mais operações intermediárias e uma terminal.
- Operação intermediária:

  - Produz uma nova streams (encadeamento)
  - Só executa quando uma operação terminal é invocada (lazy evaluation)

- Operação terminal:

  - Produz um objeto não-stream (coleção ou outro)
  - Determina o fim do processamento da stream

- Operações Intermediárias

  - filter
  - map
  - flatmap
  - peek
  - distinct
  - sorted
  - skip
  - limit (\*)

- Operações Terminais

  - forEach
  - forEachOrdered
  - toArray
  - reduce
  - collect
  - min
  - max
  - count
  - anyMatch (\*)
  - allMatch (\*)
  - noneMatch (\*)
  - findFirst (\*)
  - findAny (\*)

- Criar um Stream:

  - Basta chamar o método stream() ou parallelStream() a partir de qualquer objeto Collection.

  - Outras formas de se criar uma stream incluem:
    - Stream.of
    - Stream.ofNullable
    - Stream.iterate

- Exemplo 1:

```
public final class Main {

    public static void main(String[] args) {

        Predicate<Integer> predicado = t -> Integer.parseInt(t.toString()) % 2 == 0;
        Consumer<Integer> consumer = c -> System.out.println(c);
        Function<Integer, Integer> function = e -> Integer.parseInt(e.toString()) * 10;

        List<Integer> listaStream = Arrays.asList(1, 2, 3, 4, 5);

        listaStream.stream()
                .filter(predicado)
                .map(function)
                .forEach(consumer);

        List<Integer> teste1 = listaStream.stream()
                .filter(predicado)
                .map(function)
                .sorted(new Ordena()::compare)
                .collect(Collectors.toList());
        System.out.println(teste1);
    }
}

class Ordena implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Integer o11 = Integer.parseInt(o1.toString());
        Integer o22 = Integer.parseInt(o2.toString());
        if (o11 > o22)
            return -2;
        if (o11 < o22)
            return 1;
        return 0;
    }

}
```

- Exemplo 2:

```
List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
Stream<Integer> st1 = list.stream();
System.out.println(Arrays.toString(st1.toArray()));

Stream<String> st2 = Stream.of("Maria", "Alex", "Bob");
System.out.println(Arrays.toString(st2.toArray()));

Stream<Integer> st3 = Stream.iterate(0, x -> x + 2);
System.out.println(Arrays.toString(st3.limit(10).toArray()));

Stream<Long> st4 = Stream.iterate(new long[]{ 0L, 1L }, p->new long[]{ p[1], p[0]+p[1] }).map(p -> p[0]);
System.out.println(Arrays.toString(st4.limit(10).toArray()));
```

## Revisão Collections

- Método sort() do Collections - a classe deve implementar a interface Comparable e sobrescrever o método compareTo(). Assim a sua classe passa a ter uma implementação de sort.

- Outra Solução (mais moderna) - usar o método sort da Interface List. Nesse casso passamos um Comparator para esse sort.

  - mas por que usá-lo ? se o critério de comparação / ordenação mudar não precisamos modificar a classe de negócio. Isso provocou um desuso do método sort do Collections.

- Comparator é uma interface funcional. Podemos criar uma classe que implementa essa interface.

## Math.random() e Random

- Math.random() retorna um número pseudoaleatório do tipo double, maior que ou igual a zero e inferior a um. Digamos que nossa ideia é gerar números aleatórios dentro de um intervalo específico – por exemplo, de zero a quatro.

```
double doubleRandomNumber = Math.random() * 5;
int randomNumber = (int)doubleRandomNumber;
```

- Na classe Random, temos muitos métodos de instância que fornecem números aleatórios. Como exemplo, temos o método nextInt(int bound) retorna um número pseudoaleatório do tipo int, maior que ou igual a zero e menor que o valor de bound. A classe Random fornece vários métodos para gerar números pseudoaleatórios de tipos diferentes, como nextInt(), nextLong(), nextFloat() e nextDouble().

## Gson e Jackson

- Lib Gson > converte objeto java para objeto Json
- Lib Jackson > converte objeto Json para objeto java (tb conhecido como jackson databind)
  - ex. new ObjectMapper().readValue(responseBody, type.class)

## Conceitos Interface x Classe Abstrata

- https://youlearncode.com/java-interface/

## JPAStreamer

- https://www.freecodecamp.org/news/get-started-with-quarkus-and-jpastreamer-2/

## Thread Virtual

- https://mjovanc.com/java/java-21-a-deep-dive-into-virtual-threads/

- https://dzone.com/articles/quarkus-3-the-future-of-java-microservices-with-vi
