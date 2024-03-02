# Certificate Signing request(CSR)

- Uma solicitação de assinatura de certificado (CSR) é uma mensagem enviada a uma autoridade de certificação para solicitar a assinatura de uma chave pública e informações associadas. Mais comumente, um CSR estará no formato PKCS10. O conteúdo de um CSR compreende uma chave pública, bem como um nome comum, organização, cidade, estado, país e e-mail. Nem todos esses campos são obrigatórios e variam dependendo do nível de garantia do seu certificado. Juntos, esses campos constituem a Solicitação de Assinatura de Certificado (CSR).

- O CSR é assinado pela chave privada do requerente; isto prova à AC que o requerente tem o controlo da chave privada que corresponde à chave pública incluída no CSR. Depois que as informações solicitadas em um CSR passam por um processo de verificação e o controle do domínio é estabelecido, a CA pode assinar a chave pública do requerente para que ela possa ser publicamente confiável.

- Certificate Signing request(CSR) - solicitação de assinatura de certificado - é uma das primeiras etapas para se obter seu próprio certificado SSL/TLS. Gerada no mesmo servidor em que você planeja instalar o certificado, a CSR contém informações (por exemplo, nome comum, empresa, país) que a Autoridade Certificadora (AC) usará para criar seu certificado. Ela também contém a chave pública que será incluída em seu certificado e é assinada com a chave privada correspondente. A AC usará os dados da CSR para construir seu Certificado SSL.

  - As informações que devem constar no CSR:

    - Nome comum CN : nome de domínio totalmente qualificado (FQDN) do seu servidor - ex: \*.exemplo.com
    - Organização O: razão social da empresa
    - Unidade Organizacional OU: departamento da empresa que lida com o certificado
    - Cidade/Localidade L: cidade onde sua empresa está situada
    - Estado/Região S: estado onde sua empresa está localizada
    - País C : código de duas letras do país onde sua empresa está localizada
    - e-mail: email de contato com a sua empresa

  - Comprimento da chave. O tamanho de chave mais comum é RSA 2048.
  - A CSR em si costuma ser criada em um formato PEM baseado em Base-64.

  - Criar uma Solicitação de Assinatura de Certificado - CSR

    - pode ser criada tanto no OpenSSL quanto no Keytool
    - no link abaixo tem os videos de demonstração:
    - https://www.globalsign.com/pt-br/blog/what-is-a-certificate-signing-request-csr

  - Exemplo de geração de um CSR usando o keytool do JDK do Java:

    - Um par de chaves deve existir primeiro para gerar um CSR. Então primeiro devemos criar um KEYSTORE - armazenamento de chaves:

    ```
    keytool -genkey -alias mydomain -keyalg RSA -keystore KeyStore.jks -keysize 2048
    ```

    - Será solicitado a senha do keystore, e depois você deve repetir a senha.
    - Altere o alias acima: meudomínio - para uma palavra de sua escolha. Este alias deve permanecer o mesmo para geração de chave, geração de CSR e importação de chave pública assinada.
    - Passo 2 - gere o CSR

    ```
    keytool -certreq -alias meudomínio -keystore KeyStore.jks -file meudomínio.csr
    ```

    - Confirme ou rejeite os detalhes digitando "Sim" ou "Não" e pressionando Enter.

    - Pressione Enter para usar a mesma senha do keystore. Como alternativa, especifique uma senha separada e pressione Enter.

## ROTEIRO COMPLETO - GERACAO DE CHAVES - USANDO KEYTOOL

- Passo 1: criar um par de chaves RSA de 2048 bits, com o apelido chavesProgramandoCerto, no arquivo de keystore chamado keystoreProgramandoCerto.jks

  - Entrar no diretório bin, do seu java
  - Executar o keytool, sem esquecer de mandar o arquivo jks gerado para um diretório ao qual você tenha permissão de escrita, no caso enviarei para /home/meu_usuario

  ```
  keytool -genkeypair -alias chavesProgramandoCerto -keyalg RSA -validity 365 -keystore /home/marcelo/keystoreProgramandoCerto.jks
  ```

  - Traduzindo para o “portugol”, para os júniors de plantão…

    - FerramentaDeChave -GereUmParDeChavesPraMim -ComOApelidoDe chavesProgramandoCerto -UsandoOAlgoritmo RSA -ComAValidadeDe 365 dias -ColoqueAsChavesGeradasNoArmazenadorDeChaves /home/marcelo/keystoreProgramandoCerto.jks

  - Uma sequencia de informações será solicitada para o processo de geração das chaves e da keystore:

    - Senha da Keystore(keystoreProgramandoCerto.jks): senhaKeystore;
    - Confirmar a senha da Keystore(keystoreProgramandoCerto.jks): senhaKeystore;
    - Nome e Sobrenome: Eu Silva;
    - Unidade Organizacional(departamento): Desenvolvimento;
    - Empresa: eu sa;
    - Cidade: São Paulo;
    - Estado: São Paulo;
    - Sigla do País: br;
    - Confirmar os dados: sim ou yes;
    - Senha do par de chaves(chavesProgramandoCerto): senhaChaves;
    - Confirmar senha do par de chaves(chavesProgramandoCerto): senhaChaves;

  - Se você quiser dar uma olhada na sua keystore, pode fazer assim:

  ```
  keytool -list -v -keystore /home/marcelo/keystoreProgramandoCerto.jks
  ```

  - Nossa keystoreProgramandoCerto.jks possui agora a nossa chave pública, a chave privada e os dados e assinatura da Autoridade Certificadora, no caso, uma padrão do keytool que não tem validade real(Auto-Assinado por enquanto).

- Passo 2: podemos gerar uma requisição de assinatura – Certificate Signing Request (CSR), para assim conseguirmos um Certificado Real, assinado por uma Autoridade Certificadora. Para isso utilizaremos o nosso par de chaves chavesProgramandoCerto, criado anteriormente. Utilizaremos o comando abaixo para gerar a requisição(CSR):

```
keytool -certreq -alias chavesProgramandoCerto -file /home/marcelo/certificadoProgramandoCerto.csr -keystore /home/marcelo/keystoreProgramandoCerto.jks
```

- Traduzindo:

  ```
  FerramentaDeChave -GereUmaRequisiçãoDeCertificado –UtilizandoAChavePúblicaDoApelido chavesProgramandoCerto -ColocandoElaEOsDadosDoCertificadoNoArquivo /home/marcelo/certificadoProgramandoCerto.csr -UtilizandoAsInformaçõesNecesáriasDoAramazenadorDeChaves /home/marcelo/keystoreProgramandoCerto.jks
  ```

- Digite a senha, no nosso caso: senhaKeystore

- Passo 3: Nesse momento o arquivo certificadoProgramandoCerto.csr deve conter nossos dados pessoais digitados no processo de geração do par de chaves, as nossas chaves pública e privadas. Envie o arquivo certificadoProgramandoCerto.csr, para a sua Autoridade Certificadora predileta, de acordo com as regras da mesma. Depois de algum tempo a Autoridade Certificadora, irá lhe enviar o seu certificado assinado(depois de pedir documentos e etc), ou seja um arquivo contendo os Dados Cadastrais, as Chaves(Pública e Privada) e assinado pela Autoridade Certificadora.

  - Vamos considerar que recebemos o arquivo certifcadoProgramandoCertoReal.p7b. Só precisaremos importá-lo com o keytool. Guarde este arquivo em local seguro, em um HD e na nuvem para não correr o risco de perder e ter que fazer todo o processo novamente e principalmente $ pagar $, novamente.

  ```
  keytool -import -alias chavesProgramandoCerto -trustcacerts -file certifcadoProgramandoCertoReal.p7b -keystore /home/marcelo/keystoreProgramandoCerto.jks
  ```

  - Traduzindo:

  ```
  FerramentaDeChave -ImporteOCertificado -NoApelido  chavesProgramandoCerto -SePrecisarUseACacertsDoJavaParaValidarACadeiaDeConfiança -DoArquivo certifcadoProgramandoCertoReal.p7b -QueroQueImporteNoMeuArmazenadorDeChaves /home/marcelo/keystoreProgramandoCerto.jks
  ```

  - Se além do seu certificado, você receber o certificado da Autoridade Certificadora Raiz e uma Intermediária, primeiro execute o comando acima no Certificado Raiz, depois novamente para o Intermediário e finalmente para o seu certificado.

- Passo 4: (Deploy) A nossa keystore keystoreProgramandoCerto, já tem dentro dela tudo o que precisamos no apelido(alias) chavesProgramandoCerto. Já podemos adicionar nossa keystore em um servidor web e utilizar HTTPS(SSL), com o nosso certificado “auto-assinado”.

  - Podemos exportar o nosso certificado(apenas com a chave pública, óbvio) através do comando abaixo:

  ```
  keytool -exportcert -alias chavesProgramandoCerto -file certificadoProgramandoCertoAutoAssinado.cer -keystore /home/marcelo/keystoreProgramandoCerto.jks
  ```

  - Quem precisar utilizar nosso certificado para se conectar em nosso servidor, deverá explicar para o seu Java, que nosso certificado é “confiável”, e poderá fazê-lo através do comando abaixo:

  ```
  keytool -import -alias chavesProgramandoCerto -trustcacerts -file certificadoProgramandoCertoAutoAssinado.der -keystore /$JAVA_HOME/jre/lib/security/cacerts ou
  keytool -import -alias chavesProgramandoCerto -trustcacerts -file certificadoProgramandoCertoAutoAssinado.der -keystore /QualqueKeyStore.jks
  ```

## Exemplo manipulação certificado java

https://marceloprogramador.com/2015/07/28/guia-certificados-java/

## Referência

https://marceloprogramador.com/2015/07/28/guia-certificados-java/

https://www.globalsign.com/pt-br/blog/what-is-a-certificate-signing-request-csr
