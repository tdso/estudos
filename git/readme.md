### Notas Leitura de GIT

- git remote -v     visualiza os repo´s remotos que estão configurados

- git log origin/<nome_da_branch> visualiza os commits no remoto

- Navegar Histórico
  git checkout <file>           visualiza como um arquivo estava
  git checkout <commit>   visualiza como todos os arquivos estavam

## git checkout

- permite não apenas mudar de branch mas, também, navegarmos entre os commits. O último commit de uma branch é referenciado como head.

- podemos ir direto para um dado commit com > git checkout <numero_commit>
  ou referenciar um commit N versões antes do head usando > git checkout head~2
  nesse exemplo iriamos para o antepenúltimo commit.

<b>Importante:</b> ao mudar para uma versão de commit e acidentalmente alterar algum arquivo podemos corrigir através dos seguintes comandos:

- git reset (desfaz o git add)
- git clean -df (limpar as modificações)
- git checkout -- .
  > não pode haver alterações no arquivos, se ocorrer tem que ser desfeitas amtes de voltarmos para o head. Se quiser alterar algo crie uma branch a partir deste ponto.

## Dicas Rápidas

- remover arquivos da área de stage (após o git add)
  git status
  git reset

- desfazer modificações não salvas

  - git status
  - git reset
  - git clean -df
  - git checkout -- .

- desfazer o último commit sem desfazer as modificações nos arquivos

  - git status
  - git reset --soft HEAD~1

- deletar commits e também modificações nos arquivos - volta o projeto ao estado de um dado commit (ação destrutiva)

  - git status
  - git reset --hard <<id_commit>>
  - ex: voltar o projeto ao estado do penúltimo commit:
    git reset --hard HEAD~1

- apontar o projeto para outro repositório remoto

  - git remote set-url origin git@github.com:seuUsuario/seuRepositorio.git

- configurando sua identifcação no git

  - git config --global user.name "Seu nome"
  - git config --global user.email "Seu email de cadastro do Github"
  - git config --list

- iniciando um projeto no github
  - git init
  - git add .
  - git commit -m "Mensagem explicativa"
  - git branch -M main
  - git remote add origin git@github.com:seuusuario/seurepositorio.git
  - git push -u origin main

## Situação 1

- se eu quiser fazer alteração a partir desse ponto, deste commit tenho
        que criar uma branch a partir deste ponto, deste commit.
        git checkout -b <nome_branch>

será criada uma nova branch espelhando os arquivos do commit de onde eu estava

Desfazendo Alterações

- descartando alterações em um arquivo que ainda não foi adicionado (git add)
  ou seja não está no stage
        > git checkout --<file> ou git checkout -- . (para todos os arquivos)

- Desfazendo alterações em Arquivos no Stage (ja foi feito o git add)
  > git checkout HEAD --<arquivo>
  > o comando retira alterações e por consequência tira o arquivo da stagging area

Desfazendo um commit antigo
      > git revert <commit>

> cria um novo commit para desfazer as alterações do commit especificado

Apagando um commit > NÃO USAR se já enviou para o remoto

> use-o apenas se estiver local
> reseta o repositório para um determinado commit
>       > pode ser soft - deixa as alterações do commit que foi eliminado - não fica na stagging area
>             > git reset HEAD~1 (volta 1 commit)

> pode ser hard
>             > apaga as alterações do commit - volta ao commit anterior
>             > git reset HEAD~1 --hard (volta 1 commit)

BRANCH

Listando branchs > git branch
criando branch     > git branch <nome>
mudando de branch > git checkout <nome>

Merge

> estando na branch que deseja atualizar > git merge <nome_branch_que_possui_alteracoes>

Rebase

> estando na branch que deseja atualizar > git rebase <nome_branch_que_possui_alteracoes>
> semelhante ao merge, difere na forma de aplicar os commits
> no rebase os seus commits na frente da base, são removidos temporariamente, os commits de outra branch
> são aplicados na sua branch e por fim seus commits são aplicados um-a-um.
> caso ocorra conflitos, resolva-os, adicione as modificações ao stage (git add) e em seguida git rebase --continue

GIT FETCH

> baixa as atualizações do remoto mas não aplica no repositório
> git pull = git fetch + git merge
> sugestão autor > fetch + rebase > para manter o hostórico do desenvolvimento
> ex: a branch estava desatualizada, o instrutor fez um git fetch (baixou as atualizações) e em
> seguida fez um git rebase.

TAG

> etiquetas para definir versão estáveis
> semelhantes a branchs mas não recebe commits
> guarda um estado do repositório
> para criar uma tag no commit desejado > git tag <nome_xx>
> para consultar as tags > git tag
> para enviar ao remoto > git push origin nome_xx
> checkout na tag > git checkout nome_xx
> se quiser evoluir o projeto a partir do tag > git checkout -b manut
>       > cria uma branch chamada manut a partir da tag nome_xx

## CENÁRIO 2 - quando a branch está no remoto e não está na máquina local !?!

Se você deseja trabalhar em uma branch que existe no repositório remoto, mas não na sua máquina local, você pode criar uma cópia local da branch remota usando o comando:

- git checkout -b <nome_da_branch_local> <nome_da_branch_remota>

Isso criará uma nova branch local com o nome <nome_da_branch_local> e a configurará para rastrear a branch remota <nome_da_branch_remota>.

Em seguida, você pode trabalhar na branch local e fazer o push das alterações para a branch remota usando o comando

- git push -u <nome_do_repositório_remoto> <nome_da_branch_local>

Isso criará a branch remota e fará o push das alterações para ela.

Por exemplo, se você deseja criar uma cópia local da branch feature-branch que existe no repositório remoto origin, você pode executar o seguinte comando:

git checkout -b feature-branch origin/feature-branch

Em seguida, você pode trabalhar na branch local feature-branch e fazer o push das alterações para a branch remota feature-branch usando o seguinte comando:

git push -u origin feature-branch

Isso criará a branch remota feature-branch e fará o push das alterações para ela. Lembre-se de substituir feature-branch pelo nome da sua branch e origin pelo nome do seu repositório remoto.
