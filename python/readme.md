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

* Uma observação: os operadores de formatação de strings com % não funcionam diretamente com valores booleanos

Condicional
if (cond):
    instrucao 1     // instrucao do IF apenas o que estiver identado
    instrucao 2
else:
    instrucao 3
outra instrucao     // fora do if

> Se não se > elif
if cond:
    # instr
elif cond:
    # instr
else:
    # instr
    
Operadores Condicionais

    < > != == 

Operadores Lógicos

    and or not 

Outros Operadores
    in = verifica se um elemento está dentro de um conjunto
    ex if var in lista: 

Laços
    while condição:         // lembrar tabulação
        # bloco de código
    
    for elemento in conjunto:
        # comandos
    
    nota:
        1 - conjunto pode ser gerado pela função range(inicio, fim, passo)
            fim = valor - 1
    ex:
    for contador in range(1,11):
        print(contador)

    comandos auxiliares: continue e break



