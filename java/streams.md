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
