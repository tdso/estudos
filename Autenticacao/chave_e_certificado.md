13/09/25 - Notas Curso Alura

Chave Privada e Certificado Digital

A ferramenta OpenSSL gera tanto o certificado digital quanto a chave privada.

O certificado digital (ex server.crt - extensão crt) é a identidade do nosso servidor (aplicação),
é esse certificado que compartilhamos com as aplicações clientes. Se inspecionarmos o conteúdo do
certificado veremos os dados da empresa, a assinatura digital, a chave pública e o
inicio/fim do certificado.

A chave privada (ex server.key - extensão key) fica sempre no nosso servidor (aplicação), e não
deve ser jamais compartilhada.

Exemplo comando para gerar a chave privada e o certificado:
openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout server.key -out server.crt

Exemplo para verificarmos o conteúdo dos arquivos gerados (substitua server.crt e server.key pelo
nome do seu certificado):
a) certificado digital > openssl x509 -in server.crt -text 
b) chave privada       > openssl rsa -in server.key -text -noout 

Ao adotarmos o HTTPS em nosso projeto, criamos uma chave privada e um certificado digital.
Dessa forma, usamos uma chave pública e uma chave privada baseadas em um mecanismo de proteção
conhecido como criptografia assimétrica. As chaves estão ligadas matematicamente, o que foi
cifrado pela chave pública só pode ser decifrado pela chave privada. Isso garante que os dados
cifrados pelo navegador (chave pública) só podem ser lidos pelo servidor (chave privada).

No certificado, há uma chave pública para o cliente utilizar e o servidor mantém sua chave privada
bem guardada. Isso é um bom mecanismo de segurança, que no entanto é lento. Para facilitar esse
processo, o cliente gera uma chave simétrica na hora.

Sendo assim, ele pode criar uma chave exclusiva só para ele e o servidor com o qual está se comunicando
em um dado momento. Ela será enviada para o servidor, utilizando a criptografia assimétrica
(chave privada e pública) e então utilizada para o restante da comunicação. Essa chave simétrica gerada
para conexão entre clientes e servidores é também conhecida como chave de sessão.

Então, o HTTPS inicia com criptografia assimétrica para depois mudar para criptografia simétrica.
A chave simétrica será gerada no início da comunicação e reaproveitada nas requisições seguintes.

Wireshark

O Wireshark é um software de código aberto, disponível para diversos sistemas operacionais,
que atua na captura e análise do tráfego em uma rede em tempo real. É uma solução amplamente
utilizada para identificar o comportamento da rede, diagnosticar problemas, analisar protocolos
e investigar aspectos de segurança.

Uso:
1 - Selecione a opção Adapter Loopback traffic
2 - na barra insira o filtro: tcp.port == 8000 && http > indica para ouvir a porta 8000 e o protocolo http
