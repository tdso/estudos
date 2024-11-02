# Media.Type Server_Sent_Events

## Interface EventSource

Usada para receber eventos enviados pelo servidor (server sent events).
O client (browser) se conecta via http em um servidor e recebe eventos com o formato
text/event-stream.

A conexão permanece aberta até ser fechada pelo método close.

Vimos um rest ser chamado, mas é como se ficasse uma conexão aberta com esse rest,
que fica devolvendo o sinal que chega na fila (tópico) que o rest(client) está esperando.

Ao contrário do dos WebSockets, server-sent-events são unidirecionais, ou seja, servidor para
cliente.

Antigamente esse fluxo era possível via pooling (o browser fica acessando o servidor continuamente),
em busca de novas atualizações.

Resumindo: o Server-Sent-Events (SSE) padrão http que permite um aplicativo web lide com um fluxo
de eventos unidirecionais e receba atualizações sempre que o servidor emite os dados.

Ref. king.host/blog/2018/03/server-sent-events-o-que-e-e-como-usar
