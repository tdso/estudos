# IntelliJ

## Instalação

- baixar o arquivo compactado
- descompactar com tar -xvf nome_arq.tar.gz
- dica: se criar uma pasta em /usr/lib/jvm o intellij já busca nesta estrutura os jdk's
  - ex: mkdir -p /usr/lib/jvm/jdk-11
- mova a pasta descompactada para a pasta criada - ex: sudo mv jdk-11/\* /usr/lib/jvm/jdk-11

- configurar o linux para setar a versão do java para rodar a partir da linha de comando:

  - sudo update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jdk-11/bin/java" 1010
  - para confirmar: java -version

- FAZER o mesmo para o COMPILADOR
- sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/lib/jvm/jdk-11/bin/javac" 1010
- para confirmar: java -version

### Por fim para selecionar a versão desejada digite:

- sudo update-alternatives --config javac // para compilador
- udo update-alternatives --config java // para execução
