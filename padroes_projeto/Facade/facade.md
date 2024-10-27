# Padrão Facade (Fachada)

- Simplifica o acesso a serviços e módulos.

- Define uma fachada que provisiona uma interface única para um conjunto
  de interfaces de um sistema.

- Define uma interface de nível mais elevado que facilita a utilização do sistema.

Ex.

```
                    Modulo Cliente
                     _____________
                    |  ACESSA     |
                    |_____________|
    Modulo Service  | Modulo Pagamento | Modulo Segurança

    - No cliente teremos que instanciar vários services
    - acoplamento forte
    - mais complexo, o cliente tem acessar vários serviços

                    Modulo Cliente
                            |
                    -----------------
                    | ServiceFacade |
                    -----------------
                           |
                     ______|______
                    |  ACESSA     |
                    |_____________|
    Modulo Service  | Modulo Pagamento | Modulo Segurança

    - A classe Facade que vai instanciar em seu construtor os serviçoes
    necessários de que precisa, e expor os métodos que chamam os métodos
    desses serviços.
    - Esconde a complexidade.

```
