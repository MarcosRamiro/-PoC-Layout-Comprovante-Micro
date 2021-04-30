
### Tecnologias

- Java JDK-8
- Micronauts [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)
- Api Rest
- Docker e Docker-Compose (Server MySQL)

### MySQL

- Criar Banco de Dados: **db_comprovante** ([backup](https://www.codigofonte.com.br/dicas/backup-e-restore-no-mysql-com-mysqldump) disponível em _script/db_)

Caso necessário, utilizar os comandos abaixo para criar o usuário e conceder permissão na base de dados:
- CREATE USER 'user_comprovante'@'192.168.0.%' IDENTIFIED BY '123456'; -- Cria o usuario
- GRANT ALL PRIVILEGES ON db_comprovante.* TO 'user_comprovante'@'192.168.0.%'; -- Concede permissao na base

Para executar o MySQL, subir via Docker-Compose (disponível em _script/docker_)

### Como chamar a API

Endereço: _http://localhost:8080/comprovante/detalhe_

Método: _POST_

Header: _Content-Type=application/json | Accept=application/json_

Body: _{
"id": 100000000,
"titulo": "titulo aqui",
"tipo": "rfb",
"versao": "2",
"detalhes": [
{
"tituloAtributo": "codigo de barras",
"valorAtributo": "1234567890"
},
{
"tituloAtributo": "data do pagamento",
"valorAtributo": "30/11/2020"
},
{
"tituloAtributo": "valor do pagamento",
"valorAtributo": "R$ 301,45"
},
{
"tituloAtributo": "referência",
"valorAtributo": "123-8"
},
{
"tituloAtributo": "versao autenticador",
"valorAtributo": "v123.45"
}
]
}_ 

  
