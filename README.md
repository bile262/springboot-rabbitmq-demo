# Spring boot RabbitMQ Producer/Consumer Demo

Demo project for producer/consumer with spring boot and rabbitmq covering basic communication and also some common patterns 
like routing.

## RabbitMQ

this demo depends on a local or docker installation of rabbitmq available on localhost:5672 .

All rabbitmq configuration is done by the code once it is started.

#### Executing

you can simply run the projects *rabbitmq-consumer* and *rabbitmq-producer* on the IDE.

Or on command line with:
* `mvn clean install` on the root folder
* `mvn spring-boot:start` on each project folder

Once they are running you can use:
* `http://localhost:8081/producer` to create a new message
* `http://localhost:8080/consumer` to see the list of consumed messages.

Each produced message will be sent to a exchange that routes them towards two queues:  
* topic.queue: a topic queue where the messages will be available for the consumer

