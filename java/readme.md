# Referências Linguagem Java

## Manipulando arquivos (I/O)

- pasta arquivos - código exemplo

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
