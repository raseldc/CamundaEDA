package com.camunda.eda.CamundaEDA.controller;

import com.camunda.eda.CamundaEDA.CamundaEdaApplication;
import com.camunda.eda.CamundaEDA.kafka.EventBus;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/bike")
@Slf4j
@RequiredArgsConstructor
public class BikeRegController {
    private static final Logger LOG = LoggerFactory.getLogger(CamundaEdaApplication.class);

    private final ZeebeClient zeebeClient;
    private final EventBus eventBus;

    @PostMapping
    public ResponseEntity<String> createBike(@RequestParam("bikeId") String bikeId,
                                             @RequestParam("bikeType") String bikeType,
                                             @RequestParam("location") String location) {
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("BikeRegSaga")
                .latestVersion()
                .variables(Map.of("receivedMessage", bikeId))
                .send()
                .join();

        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }
}
