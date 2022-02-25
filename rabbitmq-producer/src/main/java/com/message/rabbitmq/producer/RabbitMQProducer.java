package com.message.rabbitmq.producer;

import com.message.rabbitmq.common.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

import static com.message.rabbitmq.common.RabbitConfiguration.*;

@Controller
@SpringBootApplication
@ComponentScan("com.message.rabbitmq")
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQProducer.class, args);
    }

    @ResponseBody
    @PostMapping("/producer")
    String producer(@RequestBody SimpleMessage message) {
        int id = counter.incrementAndGet();
        SimpleMessage simpleMessage = new SimpleMessage(id, message.getDescription(), message.getRoutingKey());
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, message.getRoutingKey(), simpleMessage);

        return "Message Produced sending... ";
    }
}
