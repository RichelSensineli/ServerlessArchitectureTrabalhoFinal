# MBA em Full Stack Developer – Microservices, Cloud e IOT
## Disciplina: Serverless Architecture

### Grupo:
##### Ana - RM334056
##### Edson - RM333819
##### Hugo - RM333823
##### Richel - RM333805



### AWS Serverless Cloud Native Java RESTful API
#### 1- Stack da API:
* Java SE 8
* AWS SAM
* Amazon API Gateway
* AWS Lambda
* Amazon DynamoDB



#### 2- Funcionalidades da API:

#####   - Criação de um novo registro de Trip:
#####   - Consulta de Trips por ID:
#####   - Consulta de Trips por período:



#### 3- Execução da API:

##### A- Localmente:
###### Download da imagem Docker do DynamoDB:
```
docker run -p 8000:8000 -v $(pwd)/local/dynamodb:/data/ amazon/dynamodb-local -jar DynamoDBLocal.jar -sharedDb -dbPath /data
```

###### Criação da tabela no DynamoDB:
```
aws dynamodb create-table --table-name trip --attribute-definitions AttributeName=id,AttributeType=S AttributeName=date,AttributeType=S --key-schema AttributeName=id,KeyType=HASH AttributeName=date,KeyType=RANGE --billing-mode PAY_PER_REQUEST --endpoint-url http://localhost:8000
```

###### Deleção da tabela no DynamoDB:
```
aws dynamodb delete-table --table-name trip --endpoint-url http://localhost:8000
```

###### Buildar o projeto para a geração dos binários da aplicação:
```
mvn install
```

###### Com o SAM CLI executar o seguinte comando para a execução da API, de acordo com seu sistema operacional:

###### Linux
```
sam local start-api --env-vars src/test/resources/test_environment_linux.json
```

###### MacOS
```
sam local start-api --env-vars src/test/resources/test_environment_mac.json
```

###### Windows
```
sam local start-api --env-vars src/test/resources/test_environment_windows.json
```

###### [AWS - Instalação SAM CLI](https://docs.aws.amazon.com/es_es/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)


##### B- AWS:
###### Criando um Bucket S3 para o Deploy:
```
export BUCKET_NAME=trip-202005
aws s3 mb s3://$BUCKET_NAME
```
(Lembre-se que o nome do Bucket deve ser único)

###### Fazer o package da aplicação:
```
sam package \
    --template-file template.yaml \
    --output-template-file packaged.yaml \
    --s3-bucket $BUCKET_NAME

```

###### Por fim, realizar o deploy da aplicação:
```
sam deploy \
    --template-file packaged.yaml \
    --stack-name trips-project \
    --capabilities CAPABILITY_IAM
```
