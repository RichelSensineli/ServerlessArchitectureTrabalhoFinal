MBA em Full Stack Developer – Microservices, Cloud e IOT
Disciplina: Serverless Architecture
Trabalho: AWS Serverless Cloud Native Java RESTful API
Nota Máxima: 10,0

Grupos: O trabalho deverá ser realizado em grupos de no máximo 4 integrantes e no mínimo 3.

Entrega:
Os trabalhos deverão ser entregues via sistema acadêmico FIAP em um arquivo
.txt contendo o nome dos integrantes e uma URL pública do GitHub, onde deverá
conter o código fonte da API.

Prazo: O prazo de entrega é de 20 dias corridos após a última aula da disciplina.

AWS Serverless Cloud Native Java RESTful API
Stack da API:
• Java SE 8
• AWS SAM
• Amazon API Gateway
• AWS Lambda
• Amazon DynamoDB

Requisitos:
• Desenvolver uma API seguindo as melhores práticas do conjunto de princípios da arquitetura REST (Representacional State Transfer).
• Para a entidade Trip contendo os atributos: id, date (YYYY/MM/DD), country, city, URL repository for fotos, desenvolver:

o Criar um novo registro de Trip:
§ Retornar HTTP Code 201
§ Retornar no body da resposta o Id da Trip e uma URL de um Bucket Amazon S3 que irá servir para posteriormente subir as fotos da viagem.
• O nome do bucket deve ser gerado com a seguinte sintaxe: <trip-country>-<trip-city>-<date><6-digit-random-number>

o Get Trips por período:
§ Via query parameter /trips?start=X&end=Y
§ Retornar 200 contendo os resultados dentro de uma lista.
§ Mesmo quando nada for encontrado, retornar lista vazia
e HTTP Code 200.
§ Retornar no body todos os campos da entidade.

o Get Trip por Id:
§ Via Path parameter /trips/55
§ Retornar somente a trip do id informado.
§ Caso encontrado HTTP Code 200, caso não encontrado corpo vazio e HTTP Code 404.
o No código fonte da API deve conter um arquivo README com instruções para rodar a API localmente e também na AWS.
Critérios de avaliação
• A API deve ser capaz de rodar com a Stack completa local.
• A API deve ser capaz de ser implantada na AWS através dos comandos AWS SAM.
• A API deve implementar corretamente todos os requisitos funcionais e não funcionais.