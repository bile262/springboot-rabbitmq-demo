package com.message.rabbitmq.common;

import java.io.Serializable;

public class SimpleMessage implements Serializable {

    private int id;
    private String description;
    private String routingKey;

    public SimpleMessage() {
    }

    public SimpleMessage(int id, String description, String routingKey) {
        this.id = id;
        this.description = description;
        this.routingKey = routingKey;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
