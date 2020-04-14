# Assignment 2: Integration
## Usecase
You are working for a bank and more and more Customer-Facing Services need access to the data you generate and process in your core-banking system. The idea is, to introduce an asynchronous EventStream that works in a non-blocking manner. The decision was made to create multiple prototypes to compare the open source products on the market. You were selected to create the prototype based on Kafka.
The scenario is pretty simple. A core-banking service publishes all the topics that are relevant for your use-case. Two services consume those topics and provide some data to a customer (UI is not relevant for this exercise). In addition a money-laundering service processes a topic and adds additional events. 

## Tasks
Fulfill the following goals for the given task at hand:
* Setup Kafka and the core-banking publisher service to generate topics
* Create the consumer services that subscribe to the topcis
* Create the money-laundering service that processes one topic

# Solution

Solution was done using the following tutorials:
* [Intro to Apache Kafka with Spring](https://www.baeldung.com/spring-kafka)
* [Setting Up and Running Apache Kafka on Windows OS](https://dzone.com/articles/running-apache-kafka-on-windows-os)