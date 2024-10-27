# Builder

- delegar a criação do objeto para um builder ao invés de instanciar o objeto
  concreto.

- divide a criação do objeto em partes.

- encapsula a criação e montagem dessas partes em um builder separado

No exemplo temos uma classe, similar a um POJO, com os atributos, get e setters,
e sem construtor.

Cria-se uma classe abstrata para funcionar como um builder, essa classe tem um atributo
protected que é setado no construtor . Essa classe tem métodos que podem ou não ser
abstratos, tornando a sua implementação opcional, nas classes que vierem extendê-las.

O atributo protected é a instância da classe que desejo criar, mas como pode ser criada
de N formas, cada classe que extender a classe abstrata pode configurá-la como desejar.

Os métodos da classe abstrata nada mais são que build's (setter) das propriedades da
instância que será criada.

Dica:

1 - o construtor dentra da classe pode ser uma classe interna (pode ser static),
permitirá construções do tipo:

- Carteira carteira = new Carteira.Builder(...);

2 - retorn objetos imutáveis;
