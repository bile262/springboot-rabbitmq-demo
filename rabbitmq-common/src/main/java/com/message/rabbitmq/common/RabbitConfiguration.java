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
    public static final String TTL_QUEUE = "ttl.queue";
    public static final String DLX_QUEUE = "dlx.queue";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String DLX_EXCHANGE = "dlx.exchange";
    public static final String TEST_TOPIC_1 = "topic.*.test";
    public static final String TEST_TOPIC_2 = "#.topic";

    //Tạo Dead Letter Exchange
//    @Bean
//    FanoutExchange newDeadLetterExchange() {
//        return (FanoutExchange) ExchangeBuilder.fanoutExchange(DLX_EXCHANGE)
//            .durable(true)
//            .build();
//    }

    //Tạo Topic Queue
    @Bean
    Queue newQueue1() {
        return QueueBuilder
            .durable(TOPIC_QUEUE_1)
//            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
            .build();
    }

    //Tạo Topic Queue
    @Bean
    Queue newQueue2() {
        return QueueBuilder
                .durable(TOPIC_QUEUE_2)
//            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .build();
    }

    //Tạo Topic Exchange
    @Bean
    Exchange newTopicExchange() {
        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE)
            .durable(true)
            .build();
    }
    //Tạo Binding Topic Queue
    @Bean
    Binding newBinding() {
        return BindingBuilder.bind(newQueue1())
            .to(newTopicExchange())
            .with(TEST_TOPIC_1)
            .noargs();
    }

    //Tạo Binding Topic Queue
    @Bean
    Binding newBinding2() {
        return BindingBuilder.bind(newQueue2())
                .to(newTopicExchange())
                .with(TEST_TOPIC_2)
                .noargs();
    }



    //Tạo TTL Queue with DeadLetter
//    @Bean
//    Queue newTTLQueueWithDeadLetter() {
//        return QueueBuilder
//            .durable(TTL_QUEUE)
//            .withArgument("x-message-ttl", 30000)
//            .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
//            .build();
//    }

    //Tạo Binding TTL Queue
//    @Bean
//    Binding newTTLBinding() {
//        return BindingBuilder.bind(newTTLQueueWithDeadLetter())
//            .to(newTopicExchange())
//            .with(TEST_TOPIC)
//            .noargs();
//    }

    //Tạo Dead Letter Queue
//    @Bean
//    Queue newDeadLetterQueue() {
//        return QueueBuilder
//            .durable(DLX_QUEUE)
//            //lazy queues move their contents to disk as early as practically possible,
//            // and only load them in RAM when requested by consumers
//            .withArgument("x-queue-mode", "lazy")
//            .build();
//    }

    //Tạo Binding Dead Letter Queue
//    @Bean
//    Binding newDLXBinding() {
//        return BindingBuilder.bind(newDeadLetterQueue())
//            .to(newDeadLetterExchange());
//    }

}
