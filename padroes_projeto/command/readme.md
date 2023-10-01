# Command Pattern

- Padrão comportamental, isto é, diz respeito a como as classes interagem.
- Separa o remetente (quem faz a solicitação) do receptor (quem executa a ação), permitindo flexibilidade em adicionar novos comandos e mantendo baixo acoplamento.
- Crie uma interface ex. Command com um método execute()
- Crie uma classe ex. CommandExecutor que possui um método que recebe como argumento um tipo de Command.
  Ex. comandExecutor(Command command)
  - O método commandExecutor deve chamar o método execute do command recebido como argumento
