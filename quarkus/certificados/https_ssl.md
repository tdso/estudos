## Habilitar HTTPS e SSL no Quarkus

1 - criar um segredo > certificado + chave para testes > openssl - openssl req -newkey rsa:2048 -new -nodes -x509 -days 365 -keyout key.pem -out cert.pem - cria 2 arquivos: key.pem com a chave privada e cert.pem com o certificado e a chave pública

2 - adicionar a secret no openshift > salvar > add secret workload

3 - configurar as variáveis de ambiente para habilitar o TLS (https): - quarkus_http_ssl_port = 8443 - quarkus.http.ssl.certificate.file = cert.pem - quarkus.http.ssl.certificate.key.file = key.pem - quarkus.http.insecure.requests = redirect

4 - Openshift = para expor um serviço a rede externa é necessário um service e uma route (rota): - service = balanceador de carga interno do pod - recebe um IP + porta - e funciona como um proxy para os POD's - route = maneira de expor o serviço dando-lhe um nome de host acessível externamente - para verificar: oc get routes e oc get services (oc get svc)

5 - Referências: - developers.redhat.com/blog/2021/01/06/how-to-enable-https
