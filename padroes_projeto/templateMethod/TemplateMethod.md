Template Method

Define um esqueleto de algoritmo para uma operação, delegando alguns passos
para as subclasses, que redefinem certos comportamentos sem mudar a estrutura
do algoritmo.

Define um conjunto de operações, uma ou mais dessas operações podem ser abstratas
e implementadas em subclasses.

As subclasses provê parte desta implementação.

Quando usar: suportar algoritmos com mesmo comportamento conceitual, mas que podem
ter implementações diferentes.

Neste padrão a super classe (template method) chama métodos abstratos, implementados
pelas subclasses que irão extender a super classe (princípio Hollywood: "don't call
us, we'll call").
