package com.camunda.eda.CamundaEDA.worker;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class Worker {
    @JobWorker(type = "process-message")
    public void processMessage(final JobClient client, final ActivatedJob job) {
        String message = (String) job.getVariablesAsMap().get("receivedMessage");
        log.info("Processing message: {}", message);

        // Add your business logic here
        Map<String, Object> variables = Map.of(
                "processedMessage", "Processed: " + message
        );

        client.newCompleteCommand(job.getKey())
                .variables(variables)
                .send()
                .join();
    }
}
