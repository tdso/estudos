# Etapas para desenvolver módulo com assinatura

1 - Preencher objetos de dados
2 - Transformar objeto em XML (marshaller)
3 - Assinar o XML com certificado digital do cliente (chave pública do cliente)
4 - Chamar o serviço com HTTPS - inserindo o certificado digital no header (entendo aqui é o meu
certificado privado para garantir que eu enviei)
5 - Obter o xml de retorno fazendo o unmarshaller e o transformando em um objeto
