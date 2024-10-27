# Padrão Flyweight

Problema: como trabalhar de forma eficiente com uma grande quantidade de objetos
em memória ?

Solução

Dividir o objeto em dados:

- intrínsecos: valores que não mudam

- extrínsecos: valores que mudam

Crie uma classe Flyweight que armezene os dados intrínsecos.
Os clientes compartilham os Flyweight adicionando os valores
extrínsecos pontualmente.
