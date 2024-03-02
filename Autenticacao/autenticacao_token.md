# Autenticação

## Autenticação por Token

### Fluxo

- Client envia login/senha
- Servidor após validá-los, gera token e envia para o client
- Padrão JWT - Json Web Token

- As informações não ficam salvas no servidor > são gravadas no token e que costuma ter um período curto de expiração

## OAUTH2

- estrutura de autorização que permite que aplicativos obtenham acesso a um recurso http em nome de um usuário.
- implementa mecanismo de autenticação baseado em token, delegando a um servidor externo a autenticação do usuário e fornecendo um token para o contexto de autenticação.

## Provedores de Identidade

- convertem o token em uma instância Security Identity
- OIDC, OAUTH2, JWT = possuem implementação Identity Provider

- Quarkus Security > fornece mecanismos de autenticação - depende do provedor de identidade
  - verifica as credenciais de autenticação e mapeia para uma instância de Security Identity (atributos como nome, funções, credenciais, etc)
  - controle baseado em função (RBAC)

## Quarkus

- fornece suporte para vários mecanismos de autenticação:
  Requisito de Autenticação Mecanismo
  user+senha autenticação básica com formulário
  token acesso autenticação OIDC, JWT, OAuht
  logon único (SSO) fluxo código OIDC
  certificado de cliente TLS Mútua

- Autenticação SmallRye JWT
  - implementa JWT e opções para verificar JWT tokens assinados e criptografados
  - considera apenas chaves pem ou jwk
  - fornece api para geração de tokens jwt
