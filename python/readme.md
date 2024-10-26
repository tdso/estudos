Divisão inteira (//)

Essa operação retorna o resultado da divisão inteira entre dois números, ou seja, retorna apenas a parte inteira de uma divisão. Por exemplo, sabemos que a divisão exata de 7 por 3 (7/3) resulta no valor 2.333333…. Caso essa fosse uma divisão inteira, o resultado dela seria apenas 2.

Módulo (%)
O operador de módulo pode retornar o resto da divisão inteira entre dois números. Lembrando que o resto de uma divisão é o número que sobra quando uma divisão não é exata.

Exponenciação (**)
Conseguimos elevar o número à uma potência definida através do operador **.

Boolean - note abaixo - a primeira letra maiúscula
True
False

Input
Entrada de dados pelo usuário
nome = input ("Informe o seu nome: ") // retorna sempre str

Conversão de Dados
int(valor) ou float (valor) ou str (valor)

Formatando Saida
print(f'Ano: {nome_variavel} - Aprovado: {nome_var}')

ou

print('Nome do aluno: %s' %(nome_aluno))
print('Nome do aluno é %s, ele tem %d anos e sua média é %f.' %(nome_aluno, idade_aluno, media_aluno))

- Uma observação: os operadores de formatação de strings com % não funcionam diretamente com valores booleanos

Condicional
if (cond):
instrucao 1 // instrucao do IF apenas o que estiver identado
instrucao 2
else:
instrucao 3
outra instrucao // fora do if

> Se não se > elif
> if cond:

    # instr

elif cond: # instr
else: # instr

Operadores Condicionais

    < > != ==

Operadores Lógicos

    and or not

Outros Operadores
in = verifica se um elemento está dentro de um conjunto
ex if var in lista:

Laços
while condição: // lembrar tabulação # bloco de código

    for elemento in conjunto:
        # comandos

    nota:
        1 - conjunto pode ser gerado pela função range(inicio, fim, passo)
            fim = valor - 1
    ex:
    for contador in range(1,11):
        print(contador)

    comandos auxiliares: continue e break

Estruturas de Dados

- Listas = armazena qq tipo de dados, e diferentes tipos de dados;
  ex :
  lista = ['maria',10, True]
  for elemento in lista:
  print(elemento)

  ex: transformar uma string em lista
  duvida = 'Quem veio antes? O ovo? Ou foi a serpente?'
  lista_palavras = duvida.split('?')
  print(lista_palavras)
  Saída ['Quem veio antes', ' O ovo', ' Ou foi a serpente', '']

  ex: O contrário também acontece, pois podemos transformar uma lista em uma string através do método join(). Para usar a função, precisamos definir o caractere unificador que será utilizado para unir os elementos da lista, formando a string.
  misturas = ['Tintas: vermelho, azul e amarelo',
  'Verde: mistura de azul e amarelo',
  'Laranja: mistura de vermelho e amarelo',
  'Roxo: mistura de vermelho e azul']
  unificador = '. '
  string_misturas = unificador.join(misturas)
  print(string_misturas)
  Saída: Tintas: vermelho, azul e amarelo. Verde: mistura de azul e amarelo. Laranja: mistura de vermelho e amarelo. Roxo: mistura de vermelho e azul

  Métodos da Lista - len() - qtde de elementos - partição = subconjunto dos elementos
  lista[inicio:fim+1]
  lista[:3] > busca até o 2 elemento
  lista[3:] > busca do elemento 3 até o fim - adicionar fim lista > append
  lista.append(elemento) - adicionar vários elementos ao final da lista
  lista.extend([elemento1, elemento2, elemento3]) - remover elelentos da lista
  lista.remove(elemento) - insert() que insere o elemento em uma determinada posição
  lista.insert(indice, elemento) - pop() remove o elemento de uma determinada posição
  lista.pop(1) - index() retorna o índice de um elemento específico na lista
  lista.index(1)

- Dicionários de dados

  - Estruturas de chave / valor - ex:
    dic = {chave: valor}
  - pop(key) = remove uma chave
  - items() - retorna todas as chaves e valores
  - keys() - retorna todas as chaves
  - values() - retorna todos os valores

  - Leitura dos valores com FOR

    for chave in dic.keys():
    print(chave + ' = ' + dic[chave])

    for chave, valor in dic.items():
    print(chave, valor)

  - Combinando Dicionários e Listas

  Podemos associar estruturas de dados a outras estruturas de dados, como acontece com as listas dentro de dicionários. Nesse caso, as listas podem ser armazenadas nos valores de um dicionário de forma que cada chave pode ter uma lista associada a ela. Isso é útil quando precisamos armazenar vários valores relacionados a uma única chave.

  loja = {'nomes': ['televisão', 'celular', 'notebook', 'geladeira', 'fogão'],
  'precos': [2000, 1500, 3500, 4000, 1500]}

  for chave, elementos in loja.items():
  print(f'Chave: {chave}\nElementos:')
  for dado in elementos:
  print(dado)

Outras funções

sum()
A função sum() permite somar os elementos de uma sequência ou estrutura de dados. No exemplo a seguir, vamos somar os preços de produtos:

precos = [100.0, 400.0, 200.0]
soma = sum(precos)
soma

help()
A função help() é usada para acessar a documentação de funções, métodos e outros elementos do Python. Ela exibe informações em inglês sobre a funcionalidade, sintaxe e uso de um objeto específico. Para usar essa função, basta passar o elemento desejado entre parênteses

help(print)

dir()
Por fim, a função dir() é usada para exibir uma lista de atributos e métodos associados a um elemento. Por exemplo, vamos descobrir todos os atributos em métodos de uma lista:

lista = [1,2,3]
dir(lista)

# Parte II

### Importando bibliotecas

- pip = gerenciador de bibliotecas do python
  ex: pip install matplotlib

  no colab para trabalhar com código de prompt de comando temos que precedê-la com
  o caracter !
  !pip instal matplotlib

  - para instalar uma versão especifíca
    !pip install -matplotlib==3.6.2

  após esse comando o ambiente deve ser restartado

  - importando para uso
    import matplotlib as matplot
    import matplotlib.pylot as plt

  - Para conseguirmos acessar todos os pacotes instalados em nosso Jupyter Notebook, no Colab, podemos escrever o seguinte código:
    // Imprimindo todos os pacotes instalados no ambiente e suas versões
    !pip list

- Usando mapplotlib
  1 - importar > import matplotlib.pylot as plt

  2 - método > bar > plt.bar (x = var1, height = var2)

  3 - importando uma função especifica > from nome_biblioteca import nome_metodo
  from random import choice - help (choice) >>> doc do método - choice - escolhe um número aleatório a partir de uma lista

        - Outro exemplo:
        ```
            from random import randrange, sample
            lista = []
            for i in range(0, 20):
                lista.append(randrange(100))
            sample(lista, 5)
        ```
        - Este código resulta na importação de 2 ou mais métodos de uma biblioteca, não necessitando repetir a importação desta a cada método desejado. Podemos, por exemplo, importar 2 métodos da biblioteca random para colher uma amostra de 5 valores de uma lista de 20 valores gerada aleatoriamente com números de 0 a 99.

        - função randrange() da biblioteca random. Essa função recebe como parâmetro o valor limite para a escolha aleatória ou um intervalo se passado o limite mínimo e máximo

        - math.pow(a,b) > a elevado a b

## Funções

- built-in functions = tarefas já existentes no python
  exs: - sum (lista) - len(estrutura) - round > help (round) ou round? > arredondar numero, dada uma precisão
  media = round(media, 1)

- def <nome>(param1,param2):
  <instruções>

- tupla = lista de valores iteraveis

  - As tuplas são estruturas de dados imutáveis da linguagem Python que são utilizadas para armazenar conjuntos de múltiplos itens e frequentemente são aplicadas para agrupar dados que não devem ser modificados

  - Para criar uma tupla, basta separar seus elementos por vírgulas e envolvê-los entre parênteses.

  - Além disso, por também ser um iterável, podemos desempacotar os dados de uma tupla passando cada valor para uma variável. Por exemplo:
    ```
    cadastro = ("Júlia", 23, "São Paulo", "SP", "Python para DS 1")
    nome, idade, cidade, estado, turma = cadastro
    ```

- uma função pode retornar uma tupla - ex:
  return (6.75, "aprovado")
  media, situacao = func() // cada valor retornado é colocado nas variáveis

- O Type Hint é uma sintaxe utilizada no Python para indicar o tipo de dado esperado de um parâmetro ou retorno de função. Ex.

```
# a nossa função recebe uma lista do tipo list e retorna uma variável do tipo float
def media(lista: list) -> float:
  calculo = sum(lista) / len(lista)
  return calculo
```

- O Docstring que é uma string literal usada para documentar módulo, função, classe ou método em Python. Ela é colocada como o primeiro item de definição e pode ser acessada .usando a função help()

```
def <nome>(<param_1>, <param_2>, ..., <param_n>):
    ''' Texto documentando sua função...
    '''
  <instruções>
  return resultado
```

- Lambda

  - var qq_var = lambda x: x + 0.5

- Lambda + map
  - sintaxe: map (lambda function, iteravel quero quero trabalhar)

## Listas

- Lista = lista ordenada de elementos que podem ser ou não do mesmo tipo
- Lista de Lista - ex:
  lista = [[a1,a2,a3], [b1,b2,b3], [c1,c2,c3]]
  lista[0][1]
  lista.append([valor]) \*\* lista >> usa colchetes

- Lista de Tuplas

  - estruturas de dados imutáveis
  - Ex:

  ```
  cadastro = ("Júlia", 23, "São Paulo", "SP", "Python para DS 1")
  print(cadastro[0]) # imprime Júlia
  print(cadastro[-1]) # imprime Python para DS 1
  ```

  - Além disso, por também ser um iterável, podemos desempacotar os dados de uma tupla passando cada valor para uma variável. Por exemplo:

  ```
  nome, idade, cidade, estado, turma = cadastro
  ```

  - lista_tupla.append((valor)) \*\* tupla >> usa parenteses

- Lista comprehension - tem que colocar colchetes

  - forma simples e concisa de criar uma lista
  - sintaxe: [expressao for item in lista]
  - aplicar uma expressão a cada item da lista
  - sintaxe condicional: [expressao for item in lista if cond]
  - sintaxe condicional: [resultado if cond else resultado_else for item in lista]
    situacao = ["Aprovado" if media >=6 else "Reprovado" for media in medias]

- zip = (build function) recebe um ou mais iteráveis e retorna-os como um iterador de tuplas onde cada elemento dos iteráveis está pareado - útil para fazer iteração simultânea
  em várias listas - ex:
  ```
  id = [1, 2, 3, 4, 5]
  regiao = ["Norte", "Nordeste", "Sudeste", "Centro-Oeste", "Sul"]
  mapa = list(zip(id, regiao))
  mapa # [(1, 'Norte'), (2, 'Nordeste'), (3, 'Sudeste'), (4, 'Centro-Oeste'), (5, 'Sul')]

          estudantes = zip(nomes, media)
          estudantes = list(zip(nomes, media))
          candidatos = [estudante[0] for estudante in estudantes if estudante[1] >= 8]
      ```

      - Para fazer o processo contrário, de transformar uma tupla iterável em listas, basta passar o operador asterisco (*) ao lado esquerdo do nome da tupla iterável que quer extrair os dados, repassando cada tupla para uma variável.
      ```
      tupla_iteravel = [('J392', 'João'), ('M890', 'Maria'), ('J681', 'José'), ('C325', 'Claúdia'), ('A49', 'Ana')]

      ids, nomes  = zip(*tupla_iteravel)

      ids = list(ids)
      nomes = list(nomes)

      print("IDs = ", ids)
      print("Nomes = ", nomes)
      ```

- Dict comprehension

  - criar um dicionário a partir de uma expressão

  - sintaxe: {chave: valor for item in lista} - {expressao_chave: expressao_valor for item in iteravel if condicao}

### Armazenando os estados sem repetição de valor

ex: estados_unicos = list(set(estados))

## Tratamento Exceção

- Try:
  instruções
  except <exception>:
  instruções
  except <exception>:
  instruções
  else:
  instruções
  finally:
  // independente de ocorrer exceção sempre será executado

- Exceção Personalizada - raise
  - raise <exceção> ("Mensagem")

## Analise Descritiva

Frequencia, Histograma, Media, Mediana, Moda, Quartis, Decis, Percentis, Medidas de Dispersão (desvio medio absoluto, desvio padrão e variância)

!pip install pandas==0.21.0

consultar a versão > pandas.**version**

### Frequência

dados['Sexo'].value_counts()

em % (normalizar) => dados['Sexo'].value_counts(normaliza=True)
melhorando > dados['Sexo'].value_counts(normaliza=True) \* 100

// coloquei em uma var = sequencie
frequencia = dados['Sexo'].value_counts(normaliza=True)
percentual = dados['Sexo'].value_counts(normaliza=True) \* 100

// em novo dataframe
dist_freq_qual = pd.DataFrame({'Frequencia':frequencia, "Percentual":percentual})
// atribuir labels
dist_freq_qual.rename(index = {0: "M", 1: "F"}, inplace=True)
// colocar titulo
dist_freq_qual.rename_axis('Sexo', axis='columns', inplace = True)
