Como utilizar o Projeto 
====================

O projeto possui está com docker e pode ser utilizado com esse comando: 

             docker-compose up 

Obs: se estiver utilizando linux pode ser necessário adicionar sudo ao comando.

Feito isso você pode acessar a api via: 

              http://localhost:8081/api/v1/payments

O banco de dados vai estar rodando também junto com o spring boot e poderá ser acessado pela porta 27017, default do mongodb

Collection Postman  
====================

Ao baixar o projeto você pode ver que há uma collection do Postman que possui exemplos de request  válidos e inválidos. Para utilizá-la basta importá-la no postman. 


Arquitetura e Tecnologias  
====================

Utilizei no projeto mongodb, spring boot e docker pois são tecnologias com as quais trabalho atualmente. 

  - Optei por utilizar o mongodb como banco de dados por conta de ele ser uma banco bem simples de configurar. 
  - Java, por er uma linguagem de programação que eu tenho mais familiaridade atualmente. 
  - Docker para facilizar a execução da infraestrutura necessária para o projeto de uma forma mais simples
  
  Sobre a arquitetura optei por criar um webservice rest onde possui um metodo de GET e um de POST, para criar um pagamento e consulta o seu status. 

