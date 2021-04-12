# desafio-java-springboot
**Catálogo de produtos com Java e Spring Boot**

Tecnologias utilizadas no desenvolvimento:
* Java 11
* Spring Boot
* Spring Cloud: Service Discovery Eureka, API Gateway Zuul
* Spring Data JPA
* H2
* Maven

Neste microserviço deve ser possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também deve ser possível realizar a busca de produtos filtrando por name, description e price.

Para colocar o microserviço em operação, clone ou baixe os 3 projetos abaixo e os execute na seguinte sequência:

* eureka-server: roda na porta padrão 8761

* api-gateway-zuul: roda na porta 9999

* product-ms: roda na porta definida "aleatoriamente"

**Obs.:** Os endpoints estão acessíveis a partir do gateway, por exemplo: http://localhost:9999/product-ms/products, de acordo com o esquema: 
*http://endereco_do_gateway/nome_do_microservico/endpoint*






