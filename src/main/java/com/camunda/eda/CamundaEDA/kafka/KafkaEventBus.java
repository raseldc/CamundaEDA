package com.camunda.eda.CamundaEDA.kafka;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaEventBus implements EventBus {



    private final KafkaTemplate<String, String> kafkaTemplateCamunda;
    private final static long sendTimeout = 3000;

    @Override
    public  void publishToCamunda(String msg)
    {
        kafkaTemplateCamunda.send("camunda8-topic", msg);
    }

}
