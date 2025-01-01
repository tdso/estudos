### Notas Maven

## Escopos

- scope: compile (default) - a dependência fica disponível em todos os classpaths do projeto.

- scope: test - disponível apenas para os pacotes de teste - as dependências com esse escopo não são empacotadas no JAR.

- scope: runtime - não é necessário para compilar o projeto apenas para executar.

## Dependência Transitiva

- quando uma dependência depende de outras - baixando a dependência, as demais
  são baixadas.

## Comandos

- mvn clean install > limpa a pasta target e gera novo build
- mvn clean install -U > necessário quando atualizamos o POM, já que força o download das dependências.
- mvn clean > limpa as builds
- mvn package > empacota o projeto (jar do projeto)

## Problema comum

- o JAR gerado não inclui as dependências.
- Solução: empacotar o JAR com as dependências (as configurações dentro do pom.xml na tag build devem estar feitas):

```
apache maven assembly plugin
```

## Refêrencias

- Conceitos, Instalação e configurando o Maven
  http://luizricardo.org/2014/06/instalando-configurando-e-usando-o-maven-para-gerenciar-suas-dependencias-e-seus-projetos-java/

- https://www.alura.com.br/artigos/conhecendo-melhor-maven

---
