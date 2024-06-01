# Junit

1 - como tratar exceções

> como verificar se um método lançou exception com Junit

assertThrows ( exception que espero, expressão lambda para chamar o método
desejado)

ex. assertThrows ( IlegalArgumentException.class ,
() -> service.calcularBonus());

II forma

try-catch

try {

    service.calcularBonus()
    fail(); // metodo do Junit que forca o erro, pois se chegar aqui falhou

} catch (Exception e) {
assertEquals("msg da Exception", e.getMessage());
}

2 - anotações

> método para inicializar variaveis antes de executar os testes

> @BeforeEach - basta usar a anotação e o método será chamado antes de cada

    um dos métodos de testes

> @AfterEach - executado após a execução de cada um dos métodos de teste

> @BeforeAll - antes de todos - executado uma única vez antes de todos os

    métodos de teste - o método tem que ser estático

> @AfterAll - depois de todos - executado uma única vez depois de todos os

    métodos de teste - o método tem que ser estático

3 - como testar metodos privados

> o método privado já está sendo chamado por algum outro método
> que estamos testando

## Referências

Junit - Guia
https://www.baeldung.com/junit-5

Guia
https://www.jetbrains.com/help/idea/junit.html

Video
https://www.youtube.com/watch?v=we3zJE3hlWE

# Mockito

## Referências

Mockito Tutorial
https://www.baeldung.com/mockito-series

Mockito
https://medium.com/@AlexanderObregon/getting-started-with-unit-testing-in-intellij-idea-junit-and-mockito-32e1eee739d9

Mockito
https://www.simplilearn.com/tutorials/devops-tutorial/mockito-junit
