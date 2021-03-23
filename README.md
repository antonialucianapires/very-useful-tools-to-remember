# VUTTR (Very Useful Tools to Remember)
Aplicação simples de repositório para gerenciar ferramentas com seus respectivos nomes, links, descrições e tags.

## Funcionalidades

- Listar ferramentas cadastradas
- Filtrar ferramentas utilizando uma busca por tag
- Cadastrar uma nova ferramenta
- Remover uma ferramenta por ID
- Criar usuário
- Listar usuários
- Autenticação e autorização com Oauth2

## Tecnologias

Tecnologias utilizadas:

- Java 11
- Spring Framework
- Maven 
- Docker

## Instalação
Para utilizar a primeira versão, via Docker, realize o pull da imagem abaixo:
```sh
docker pull antonialucianapires/vuttr-app
```
Em seguida, gere e execute o container:
```sh
docker run -p 3000:3000 antonialucianapires/vuttr-app:1.0.0
```

Para utilizar a primeira versão, através de sua IDE, realize o git clone do código fonte:
```sh
git clone https://github.com/antonialucianapires/very-useful-tools-to-remember.git
```
Em seguida, utilize o comando para baixar e atualizar dependências: 
```sh
mvn install 
```
## Documentação com Swagger
![capa-doc](https://user-images.githubusercontent.com/46853268/112097292-81843f00-8b7e-11eb-9f97-163195d2e2d6.png)
Para acessar a documentação (em construção), acesse a rota: 
```sh
http://localhost:3000/swagger-ui.html
```
## Exemplo

### 1. criar usuário
```sh
localhost:3000/api/tools/users
```
### 2. gerar token de usuário
Nos parâmetros username e password, insira seu usuário e senha respectivamente para gerar o token:
```sh
http://localhost:3000/oauth/token?grant_type=password&username={usuário}&password={senha}
```
- Além disso, você deve incluir no client(postman, insomnia, curl) a autenticação Basic Auth com username=client-id e password=secret-id

Exemplo de resposta:
```sh
{
      "access_token": "0f51d355-daa2-4e39-b979-5a56d6f5f019",
      "token_type": "bearer",
      "refresh_token": "e8282c75-f1ac-451b-80f9-3567aafc382b",
      "expires_in": 86399,
      "scope": "read write trust"
}
```

### 3. cadastrar nova ferramenta
Para cadastrar uma nova ferramenta, utilize a rota a seguir:

- Além disso, você deve incluir no client(postman, insomnia, curl) a autenticação Bearer - Token={token_gerado}
```sh
 POST /tools Content-Type: application/json 
localhost:3000/api/tools
```
request(body):
```sh
 {
     "title": "hotel",
     "link": "https://github.com/typicode/hotel",
     "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
     "tags":["node", "organizing", "webapps", "domain", "developer", "https", "proxy"]
 } 
```

response:
```sh
{
  "id": 3,
  "title": "hotel",
  "link": "https://github.com/typicode/hotel",
  "description": "Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.",
  "tags": [
    {
      "name": "node"
    },
    {
      "name": "organizing"
    },
    {
      "name": "webapps"
    },
    {
      "name": "domain"
    },
    {
      "name": "developer"
    },
    {
      "name": "https"
    },
    {
      "name": "proxy"
    }
  ]
}
```


MIT

   
