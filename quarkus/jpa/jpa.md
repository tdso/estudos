# JPA

Check-List Criação das Classes

1. inserir os atributos básicos
2. inserir as associações, atributos de tipos complexos;
3. construtores (sempre deixar um construtor vazio e coleções não entram no construtor)
4. get e setter's - coleções apenas get
5. hash code/ equals
6. serialização
7. incluir as anotações da associação

Anotação @Entity

@Entity: Marca uma classe como uma entidade JPA (Java Persistence API).
            Isso significa que a classe está mapeada para uma tabela no banco de dados.
            Cada instância da classe representa uma linha na tabela.

Anotação @Table

@Table: Especifica a tabela no banco de dados com a qual a entidade está mapeada.
            Você pode definir o nome da tabela, o esquema e os índices usando essa anotação.

Diferença entre @Entity e @Table

@Entity: Indica que a classe é uma entidade JPA e deve ser mapeada para uma tabela
       no banco de dados.

@Table: Fornece detalhes adicionais sobre a tabela, como o nome da tabela,
       o esquema e os índices.

Mapeamento de Algumas Colunas
Não é um problema mapear apenas algumas colunas da tabela. Você pode mapear
apenas as colunas que são relevantes para a sua aplicação. As colunas não mapeadas
serão ignoradas pelo JPA.

## Relação N x N

Produto N* ------------- N* Categoria

1 - escolha uma das classes, vou escolher Produto
2 - na classe Produto - teremos um atributo do tipo coleção chamado categorias - ex: Set<Categoria> categorias = new HashSet<>(); - anotamos com @ManyToMany - anotamos com @JoinTable ( name="nome_da_tabela_associacao",
joinColumns="nome_chave_estrangeira_da_entidade_produto - ex: produto_id",
inverseJoinColumns=@JoinColumn(name="nome_chave_estrangeira_outra_entidade - ex: categoria_id")
)

3 - na entidade Categoria - teremos um atributo do tipo coleção chamado produtos - ex: Set<Produto> produtos = new HashSet<>(); - anotar @ManyToMany (mappedBy="nome do atributo na entidade Produto que mapeou o relacionamento -
no exemplo acima - categorias")

PS: quando testar com Postman deve ocorrer o loop infinito na deserialização, então devemos usar a anotação
@JsonIgnore em uma das entidades, nesse caso, colocaremos no atributo produtos da entidade Categoria.

## Relação N x N com novos Atributos

```
Produto 1.._ ------------- 1.._ Pedido
                    |
                ItemPedido
         atributo qtde e preço
```

Problema: na OO não existe o conceito de chave primária composta, ex: id_produto + id_pedido

Solução: o atributo identificador do objeto é um só => criar uma classe auxiliar para
representar o par: pedido e item.

Passo-a-Passo

1. criar a classe para representar a chave primária - ex. pacote model.pk - PedidoItemPK
2. atributos:

```
Pedido  pedido
Produto produto
```

3. Hashcode / equal/ Serialize
4. Incluir a anotação: @Embeddable para a classe
5. Incluir a cardinalidade dos atributos Pedido e Produto: @ManyToOne, além da anotação
   @JoinColumn(name="pedido_id") para Pedido e @JoinColumn(name="produto_id") para Produto -
   @JoinColumn = aponta para o nome da chave estrangeira na tabela de relacionamento.

- Criamos a classe para a chave composta com os relacionamentos. Agora criaremos a classe
  fruto da relação N x N

6. Criar a classe PedidoItem
7. Inserir os atributos: o tipo criado acima PedidoItemPK id (não esquecer de instanciar), Qtde e Preco
8. Criar construtores (sempre um vazio pelo menos)
9. hashcode/equal/serializable
10. No construtor precisamos receber além da qtde e preco, Pedido e Produto, para podermos setá-los no
    atributo id (do tipo PedidoItemPK)
11. incluir get/setter para pedido e produto (usando o atributo id)
12. Criar os mapeamentos:
    - @Entity e @Table
    - no atributo composto (id) inserir @EmbeddedId

Agora na classe Pedido: 11. criar o atributo: Set<PedidoItem> itens 12. anotá-lo com @OneToMany (mappedBy="id.pedido") ## note o uso do atributo composto 13. fazer o get para este atributo: itens
Desta forma pedido irá conhecer itens.

PS. Todo local que tiver mapeamento de mão-dupla, um deles deve ser anotado com @JsonIgnore
em um dos campos. No caso acima o método getItens() que deve ser anotado, já que ele devolve
os itens.

Por fim, a entidade produto, temos:

14. terá uma coleção do tipo PedidoItem, similar a entidade Pedido;
15. mapeá-lo com @OneToMany (mappedBy="id.produto")
16. criar o método getPedidos e neste caso o método deve criar uma lista local
    para armazenar os pedidos que estarão em itens e retornar essa lista;
17. na entidade PedidoItem incluir @JsonIgnore no método getProduto para não termos o
    loop infinito;

PS: o exemplo acima foi para fins didáticos, na prática não buscaremos os pedidos quando
pesquisarmos por produto, então vamos retirar o @jsonIgnore da entidade PedidoItem e inclui-lo na entidade
Produto no método getPedidos.

## Relação 1 x 1

Pagto ------ Pedido

Em cada uma das entidades haverá um atributo do tipo da associação.

Em pagto terá um atributo de Pedido, e em Pedido haverá um atributo de pagto.

Para colocarmos os mapeamentos devemos:

1. identificar a classe dependente, a classe que para existir depende da outra, neste caso pagto;
2. na entidade pagto incluimos para o atributo Pedido as anotações @OneToOne e @MapsId
3. na entidade independente, Pedido, anotamos o atributo pagto com @OneToOne(mappedBy="nome_do_atributo_outra_classe - ex pedido",
   cascade=CascadeType.All)
   cascade=CascadeType.All => diz para JPA usar o mesmo id de pedido na instância de pagto, isto é, terão mesmo valor de chave

## Relação N x 1

```
            Pedido * ------------------------- 1 User
            User user                           List<Pedido> pedidos
            @ManyToOne                          @OneToMany(mappedBy="user") - nome do atributo na outra entidade
            @JoinColumn(name="user.id")
```

Em um dos lados deve ter o @JsonIgnore.

PS. a propriedade jpa-open-in-view deve ser igual a true

## Notas Explicativas

### @JoinColumn

A anotação @JoinColumn em JPA (Java Persistence API) é usada para definir a coluna que será usada para fazer a junção entre duas tabelas em um relacionamento de banco de dados.

Ela é normalmente usada em relações @OneToOne e @ManyToOne para especificar a coluna de junção na entidade proprietária. Lembre-se que
fisicamente a tabela não guarda o objeto e sim uma referência para o objeto.

Exemplo:

```
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
}
```

Neste exemplo, @JoinColumn(name = "departamento_id") indica que a coluna departamento_id na tabela Funcionario será usada para se referir à chave primária da tabela Departamento. O atributo nullable = false especifica que essa coluna não pode ser nula.

<b>Basicamente, ela define como os dois lados do relacionamento são mapeados no banco de dados.</b>
