API de Pagamentos 
====================

Este é um mock do api de pagamentos que possui apenas um metodo para a criação de um pagamento. 

Tipos de Pagamentos Aceitos 
====================

Esta API aceita apenas dois tipos de pagamento: 
1. creditCard
2. ticket 


Validações Da API 
====================
Esta API faz uma validação do Numero do cartão de Credito e também do Tamanho de um boleto. 

1. Cartão de Credito Válido
    Cartão que passou pela serviço de validação implementado na na api. 

 2. Boleto Valido
    Valido apenas se o tamanho dele é de 47 Caracteres, que foi o que encontrei sobre as linhas digitaveis de boletos. 

Como utilizar o Projeto 
====================

Criei um dockerfile do projeto que ode ser utilizado da seguinte maneira: 

    docker build -t payments-rest-api .

Primeiramente, vamos criar imagem do projeto spring-boot que posteriormente poderá ser utilizada através do docker. 

Para utilizá-la devemos utilizar: 

    docker run -p 8081:8081 rest-payments-api

Onde vamos rodar a aplicação na porta 8081, e para acessá devemos utilizar o endpoint 

    http://localhost:8081/api/v1/payments