# Singleton

- Como garantir que uma classe tenha uma única instância ?

- Podemos esconder o construtor da classe.

- definir um ponto de criação estático, que retorna esta instância única.

Ex. pool de conexões com o BD

Implementação:

- método static getInstance() = por ser estático não pode ser interface
