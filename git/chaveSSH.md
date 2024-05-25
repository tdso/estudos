## Gerar chave SSH para GitHub

1 - gere uma nova chave ssh
ssh-keygen -t ed25519 -C "your_email@example.com"

2 - adicione a chave ao agente

- inicie o agente: eval "$(ssh-agent -s)"
- adicione a chave: ssh-add ~/.ssh/id_ed25519

3 - adicione a chave publica no github

- com o seguinte comando veja o conteudo da chave e copie-o:
  cat ~/.ssh/id_ed25519.pub

Issues:

1 - se continuar solicitando usuário e senha temos que alterar a url
vinculada ao repo remoto para não usar mais https: - use o comando para visualizar a url: git remote -v - use o comando para alterar a url git do repositório:
set-url origin git@github.com:USUARIO/REPOSITORIO.git

      substitua USUARIO pelo seu nome de usuário no GitHub e REPOSITORIO
      pelo nome do seu repositório. Ex:
         git remote set-url origin git@github.com:tdso/server-channel.git
