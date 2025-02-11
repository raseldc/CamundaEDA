package com.camunda.eda.CamundaEDA.worker;

import com.camunda.eda.CamundaEDA.kafka.EventBus;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BikeReg {

    private final EventBus eventBus;

    public BikeReg(EventBus eventBus) {
        this.eventBus = eventBus;

    }

    @JobWorker(type = "BikeRegStart")
    public void processMessage(final JobClient client, final ActivatedJob job) {
        String message = (String) job.getVariablesAsMap().get("receivedMessage");
        log.info("Bike Reg Start for {}",message);
        String bikeId = message.split("-")[0];
        String purpuse = "StateDbUpdate";
        message = bikeId+"-"+purpuse;
        eventBus.publishToCamunda(message);
    }

    @JobWorker(type = "StateDbUpdate")
    public void stateDbUpdate(final JobClient client, final ActivatedJob job) {
        String message = (String) job.getVariablesAsMap().get("receivedMessage");
        //do state db update
        log.info("State DB Update for {}",message);
        String bikeId = message.split("-")[0];
        String purpuse = "StateDbUpdateConfirm";
        message = bikeId+"-"+purpuse;
        eventBus.publishToCamunda(message);
    }


    @JobWorker(type = "ReadDbUpdateEvent")
    public void ReadDbUpdateEvent(final JobClient client, final ActivatedJob job) {
        String message = (String) job.getVariablesAsMap().get("receivedMessage");
        //do state db update
        String bikeId = message.split("-")[0];
        String purpuse = "ReadDbUpdateConfirm";
        message = bikeId+"-"+purpuse;
        log.info("Read DB Confirmed for {}",message);
        eventBus.publishToCamunda(message);
    }

    @JobWorker(type = "BikeRegDone")
    public void BikeRegDone(final JobClient client, final ActivatedJob job) {
        String message = (String) job.getVariablesAsMap().get("receivedMessage");
        //do state db update
        log.info("Reg Done for {}",message);
       // eventBus.publishToCamunda(message);
    }
}
