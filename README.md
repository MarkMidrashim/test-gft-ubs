LACD
====

Projeto para test UBS (Ler, Armazenar, Calcular Dados)


### Tecnologias Utilizadas
* Java 8
* SpringBoot 2.3.9.RELEASE
* Maven
* Git
* MySQL 5.7
* Docker
* Swagger 3
* JUnit

### Execução e Acesso
Para executar o projeto é necessário instalar o Docker. Caso possua instalado, acesse a raiz do projeto, e execute o comando:
```Bash
docker-compose -d --build
```

<br>

Para validar se o programa está rodando, [acesse](http://localhost:8080/swagger-ui/index.html).
Deverá aparecer a página do Swagger.

O endpoint principal é localizado como `/api/v1/product/`.
Existem 3 métodos para o endpoint em questão:
* GET /findByProduct/{Nome_Produto}

* GET /saleProductsPerShopkeepers?product={Nome_Produto}&shopkeepers={Quantidade_Lojistas}

* POST /upload

---

1. Execute o POST com o body no tipo `form-data`, insira a key files e adicione os arquivos para upload.

2. Realize uma consulta com findByProduct para verificar se os itens foram carregados.

3. Realize a venda de produtos por lojistas