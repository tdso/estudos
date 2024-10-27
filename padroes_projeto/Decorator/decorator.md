# Padrão Decorator

- Extende a funcionalidade de uma classe que já está pronta.

- Alternativa a herança.

- Ex tem uma classe Drink, com os métodos:

  - getPrice()
  - serve()

- Crio uma classe DrinkDecorator que tem um atributo do tipo Drink,
  e note que pode ser uma classe abstrata, não precisa de implementação,
  apenas declarar novos métodos.
