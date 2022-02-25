package com.message.rabbitmq.common;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String TOPIC_QUEUE_1 = "topic.queue.1";
    public static final String TOPIC_QUEUE_2 = "topic.queue.2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String TEST_TOPIC_1 = "topic.*.test";
    public static final String TEST_TOPIC_2 = "#.topic";

    //Tạo Topic Queue1
    @Bean
    Queue newQueue1() {
        return QueueBuilder
                .durable(TOPIC_QUEUE_1)
                .build();
    }

    //Tạo Topic Queue2
    @Bean
    Queue newQueue2() {
        return QueueBuilder
                .durable(TOPIC_QUEUE_2)
                .build();
    }

    //Tạo Topic Exchange
    @Bean
    Exchange newTopicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE)
                .durable(true)
                .build();
    }

    //Tạo Binding Topic Queue1
    @Bean
    Binding newBinding() {
        return BindingBuilder.bind(newQueue1())
                .to(newTopicExchange())
                .with(TEST_TOPIC_1)
                .noargs();
    }

    //Tạo Binding Topic Queue2
    @Bean
    Binding newBinding2() {
        return BindingBuilder.bind(newQueue2())
                .to(newTopicExchange())
                .with(TEST_TOPIC_2)
                .noargs();
    }

}
