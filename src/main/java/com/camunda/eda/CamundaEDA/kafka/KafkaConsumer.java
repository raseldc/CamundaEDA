package com.camunda.eda.CamundaEDA.kafka;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ZeebeClient zeebeClient;

    @KafkaListener(topics = "camunda8-topic", groupId = "microservice")
    public void consume(String message) {
        try {
            // Publish message to Camunda 8
            if(message.split("-").length==1)
            {
                zeebeClient.newPublishMessageCommand()
                        .messageName("MessageReceived")
                        .correlationKey("1234")
                        .variables(Map.of("receivedMessage", message))
                        .send()
                        .join();
                return;
            }
            String bikeId = message.split("-")[0];
            String purpuse = message.split("-")[1];

            if (purpuse.equals("StateDbUpdate")) {
                zeebeClient.newPublishMessageCommand()
                        .messageName("MessageStateDBUpdate")
                        .correlationKey("1")
                        .variables(Map.of("receivedMessage", message))
                        .send()
                        .join();
            } else if (purpuse.equals("StateDbUpdateConfirm")) {
                zeebeClient.newPublishMessageCommand()
                        .messageName("MessageStateDBCofirm")
                        .correlationKey("2")
                        .variables(Map.of("receivedMessage", message))
                        .send()
                        .join();
            } else if (purpuse.equals("ReadDbUpdateConfirm")) {
                zeebeClient.newPublishMessageCommand()
                        .messageName("MessageReadDbConfir")
                        .correlationKey("3")
                        .variables(Map.of("receivedMessage", message))
                        .send()
                        .join();
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
