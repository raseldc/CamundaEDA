package com.camunda.eda.CamundaEDA.controller;


import com.camunda.eda.CamundaEDA.CamundaEdaApplication;
import com.camunda.eda.CamundaEDA.kafka.EventBus;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(CamundaEdaApplication.class);

    private final ZeebeClient zeebeClient;
    private final EventBus eventBus;
    @PostMapping
    public void sendMessage(@RequestParam(name = "msg") String message) {
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("message-process")
                .latestVersion()
                .variables(Map.of("total", 100))
                .send()
                .join();
        LOG.info(String.format("started a process: %d", event.getProcessInstanceKey()));

    }

    @PostMapping("/start-process")
    public void startProcess() {
        System.out.println("Start");

//        zeebeClient.newCreateInstanceCommand()
//                .bpmnProcessId("message-process")
//                .latestVersion()
//                .send()
//                .join();

        		var event = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId("message-process")
				.latestVersion()
				.variables(Map.of("total", 100))
				.send()
				.join();
        LOG.info(String.format("started a process: %d", event.getProcessInstanceKey()));
    }
}
