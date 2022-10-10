# Padrões de Projeto

![imagem](diagrama_1.png)

## Padrões Criacionais

### Factory Method

- Como deixar o código desacoplado das classes concretas?
- O método Factory Method decide qual implementação retornar de uma instância baseado em alguma regra.
  ![imagem](iphone.png)
- A classe factory deve conseguir criar a instância de cada um dos tipos de iphone de acordo com os valores informados.

```
  abstract class IphoneFactory {
    public Iphone orderIphone(){
        Iphone iphone = null;
        iphone = createIphone();
        iphone.getHardware();
        iphone.getAssemble();
        iphone.getCertificate();
        iphone.getPack();
        return iphone;
    }
    protected abstract Iphone createIphone();
    ...
  }
```

<b>Variações</b>

- o método abstrato createIphone() sem argumento sugere que devemos ter uma classe concreta para cada tipo de Iphone.
- pode-se passar um argumento para o método orderIphone() para que ele possa decidir qual instância criar. Assim esse método deve ser static e o método abstrato não seria mais necessário.

### Abstract Factory

- Como escrever um código onde as classes instanciadas possam variar dentro da mesma interface?
- Como garantir que um conjunto de objetos relacionados possam ser criados mantendo um contexto único (cenário de aplicação)?

- Extraindo a lógica de criação dos objetos para um abstract factory.
- Criando uma implementação do abstract factory para cada contexto, garantindo que todos os objetos estão relacionados.

Para ilustrar, tomemos o exemplo acima do Iphone. Consideremos que as operações de certificação e empacotamento variam de pais para pais. Podemos decompor em interfaces as operações e depois criar uma interface génerica que engloba as outras interfaces (operações).

```
  Interface Packing;
  Interface Certificado;

  Interface CountryRules {
    Interface packing;
    Interface Certificado;
  }
```

- Com base no modelo acima passo a ter implementações concretas para cada interface.

- Atentar para quando tiver operações que podem variar em função de alguma variável, se não essas operações não seriam candidatas a serem extraídas para interface.

### Padrão Singleton

- Como garantir que uma classe tenha uma única instância?

- Escondendo o construtor da classe com modificador private, e definindo um ponto de criação único, estático, que retorna uma única instância.

- Ressalva: a necessidade de ter um método estático inviabiliza o uso de interface. Como contorno temos o padrão monostate:
  - atributo private, mas static - inicializado em um bloco static;
  - assim a classe pode ter um construtor público;
    Desta forma você pode ter N instâncias compartilhando a variável static.

### Padrão Builder

- Delegar a criação do objeto para um builder ao invés de instanciar o objeto concreto.
- Divide a criação do objeto em partes.
- Encapsula a criação e a montagem dessas partes em um builder separado.
- Os métodos da classe Builder nada mais são que os setters das propriedades do objeto que se deseja criar. Os métodos do Builder retornam a instância do builder, permitindo que tenhamos uma API fluente e um único método que retorna a instância desejada após as propriedades terem sido configuradas.

#### Padrão Builder com inner Class

- trabalha com objeto imutável - sem métodos set
- construtor dentro da classe (classe interna)
- a classe builder é interna (static), a classe externa é similar a um POJO sem os métodos setters. Os atributos da classe externa devem ser final.
- como vimos acima os métodos da classe builder retornam um builder e um único método retorna o objeto que o builder propõem a criar.

```
  Carteira carteira = new Carteira.Builder();
```

    - Carteira seria a classe do tipo POJO e Builder a classe interna

- PS > cuidado com listas > só o final não garante a imutabilidade das listas

### Padrão Prototype

- Usa o conceito de cópia do objeto.
- Diferenciar cópia rasa da profunda.
- Clone do Java = cópia rasa - quando temos um objeto dentro do outro, esse objeto interno compartilha o mesmo endereço quando o objeto é clonado.
- Cópia profunda temos que reescrever o método clone, para isso temos que implementar a interface Clonable.

```
  User user = new User ("John", 25, new Address("Wall Street, 12"));
  ...
  public User clone() throws CloneNotSupportException {
    User userClone = (User) super.clone();
    userClone.address = (Address) user.address.clone();
    return userClone();
  }
```

- Se address tiver outro objeto dentro, implemente o clone na classe deste objeto, e assim um clone vai chamando o outro.
