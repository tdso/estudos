## Sistemas Reativos

- projetados para reagir a um estímulo, como uma mensagem, requisição, métrica

  - processamento de fluxo con contra pressão sem bloqueio
  - 4 propriedades sistema reativo
    - responsivo : resultado combinação elasticidade + resiliência para responder no tempo adequado
    - resiliente : quando uma instância falha o trafégo é redirecionado para as demais
    - elasticidade : aplicação funciona com N instâncias - pode crescer a medida que o trafégo aumenta
    - message-driven : o uso de mensagens assíncronas é a chave que habilita a elasticidade e a resiliência e leva a responsividade.

- Sistemas reativos confiam nas mensagens assíncronas para comunicar entre microserviços, garantindo baixo acoplamento entre as mensagens, isolamento, e transparência de localização das mensagens.

- Contra-pressão (backpressure) - as mensagens podem ser encaminhadas para muitas instâncias e podemos controlar o fluxo entre produtores de mensagens e os consumidores de mensagem.
