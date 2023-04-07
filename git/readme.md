### Notas Leitura de GIT

- git remote -v     visualiza os repo´s remotos que estão configurados

- git log origin/<nome_da_branch> visualiza os commits no remoto

- Navegar Histórico
  git checkout <file>           visualiza como um arquivo estava
  git checkout <commit>   visualiza como todos os arquivos estavam

Situação 1

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
