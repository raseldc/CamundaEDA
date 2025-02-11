package com.camunda.eda.CamundaEDA.kafka;

public interface EventBus {

    public  void publishToCamunda(String msg);
}