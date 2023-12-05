O Git fornece uma página do man para cada um desses subcomandos.
Por exemplo, você pode visualizar a página do man para o subcomando
add executando:
git help add

Para criar um commit, você deve primeiro preparar as alterações. Todas as alterações "não
preparadas" não serão incluídas no commit. A necessidade de primeiramente preparar alterações
permite que você controle quais alterações receberam commit. Esse nível de controle é
útil quando você deseja salvar parte de suas alterações. Por exemplo, quando você está
desenvolvendo um recurso grande, pode fazer pequenos commits com as partes que você acha
que estão completas.

Adicione as alterações de um arquivo para a preparação com o subcomando git add e
especifique o nome do arquivo. Para remover as alterações do estágio, use git reset.
Adicione ou remova arquivos alterados da preparação individualmente ou recursivamente
especificando um arquivo ou diretório, respectivamente.

Depois de ter preparado as alterações, você pode usar o git commit para criar um commit.
Depois do commit das alterações, você pode continuar fazendo alterações sabendo que pode
facilmente restaurar as versões anteriores. Certifique-se de fazer commit logo e com
frequência, pois os commits permitem que você restaure seus arquivos para o estado
capturado.

## Configurando Informaçẽs da sua conta

Use o comando git config para configurar seu nome de usuário

> git config --global user.name "Your User Name"

Use o comando git config para configurar seu e-mail de usuário

> git config --global user.email your@user.email

Use o comando git config para revisar suas configurações de identidade:

> git config --list

Renomeie o branch master atual (o padrão no Git) para main

> git branch -M main

O comando git show serve para visualizar o commit mais recente e as alterações feitas
nos arquivos do repositório:

> git show

Use o comando git reset para remover a preparação das alterações (retirar da area de stage -
retornar o arquivo para o estado de modificado - desfaz o git add):

> git reset HEAD arquivo

Use o comando git checkout para desfazer as alterações locais de um arquivo. Isto é,
retorna o arquivo para o seu estado original no commit atual.

> git checkout -- arquivo

## Definição de branches

Conceitualmente um branch abriga um conjunto de commits que implementam alterações ainda
não prontas para o branch principal. Os colaboradores devem criar commits em um novo branch
enquanto trabalham em suas alterações.

O comando principal para gerenciar branches é o git branch. Esse comando recebe um único
argumento: o nome do novo branch. Se já existir um branch com o mesmo nome, o comando
falhará.

> git branch my-branch

### Navegação em branches

O comando git checkout é amplamente responsável por navegar entre locais no repositório.
Ele recebe um único argumento de onde você gostaria de ir, geralmente um nome de branch.

> git checkout my-bugfix

## Merge

Depois que um branch é considerado concluído, suas alterações ficam prontas para serem
incorporadas de volta ao branch principal. Isso é chamado de mesclagem e é tão importante
quanto branches.

Se um branch criar uma divergência no histórico, a mesclagem converge de volta.
O comando do Git para mesclagem é git merge. Ele aceita um único argumento para o branch
de onde ser mesclado. Implicitamente, seu branch atualmente verificado é o branch no qual
ocorrerá a mesclagem (que receberá as alterações).

### Merge com conflitos

Para cada conflito detectado pelo Git, ele modifica o arquivo para incluir as duas alterações.
Ele envolve cada opção em marcadores de conflito e coloca o repositório local em um estado de
resolução de conflitos.
Marcadores de conflito aparecem nos arquivos como sequências de <, >e =, conforme mostrado
no seguinte exemplo:

```
    <<<<<<< HEAD
    Hi there!
    =======
    Hello there!
    >>>>>>> your-feature-branch
```

Nesse estado, os únicos comandos válidos são para corrigir os conflitos ou anular a mesclagem
com git merge --abort.

Para continuar a mesclagem, abra os arquivos e os corrija. Certifique-se de remover todos
os marcadores de conflito. Depois, prepare os arquivos usando git add. Por fim, conclua a
mesclagem usando git commit.

## Branches Remotos

Depois de clonar o repositório, você pode navegar até a pasta do repositório local
recém-criada e verificar se o Git configurou um remoto que aponta para o repositório
remoto. Use o comando git remote -v para isso.

```
[user@host ~]$ cd your-repo
[user@host your-repo]$ git remote -v
origin https://github.com/your_github_user/your-repo.git (fetch)
origin https://github.com/your_github_user/your-repo.git (push)
```

Com um remoto associado ao seu repositório local, você agora está pronto para enviar seus
commits locais para o repositório remoto. Quando você cria novos commits em seu branch local,
o branch de rastreamento remoto e o branch remoto ficam desatualizados em relação ao branch
local.

Para enviar as alterações, use o comando git push, especificando o remoto e o branch que
você deseja enviar. Ao usar o comando git push você envia o branch local selecionado no
momento.

```
[user@host your-local-repo]$ git status
On branch my-new-feature
...output omitted...
[user@host your-local-repo]$ git push origin my-new-feature
```

Para recuperar os commits mais recentes do repositório remoto e atualizar branches de
rastreamento remoto, use git fetch.
O comando git fetch atualiza seu repositório local baixando branches, commits e
outras referências Git e objetos. Por padrão, esse comando atualiza apenas branches de
rastreamento remoto.

Como alternativa à execução de obtenção e mescla manualmente, você pode usar o comando
git pull para atualizar seu branch local em um comando.

## TAG

Quando o Git é seu sistema de controle de versão, você pode usar tags do Git para nomear
exclusivamente suas versões ou versões. No Git, uma tag é um ponteiro estático para um
momento específico no histórico de commits, como versões ou versões. Diferentemente dos
branches, o ponteiro da tag não é modificado ao longo do tempo.

Em tags leves, o Git armazena apenas o nome e o ponteiro do commit. O comando git tag cria
uma tag leve. [user@host ~]$ git tag 1.2.3
Para consultar a tag criada:

> git tag

Por padrão, o Git não envia tags para o remoto. Para enviar tags, você deve usar o
parâmetro --tags:

> git push --tags
