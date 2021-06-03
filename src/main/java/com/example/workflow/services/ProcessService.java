package com.example.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;

import com.example.workflow.model.WorkflowInfo;

import lombok.extern.log4j.Log4j;
@Log4j
public class ProcessService {

    @Autowired
    private ProcessRuntime processRuntime;
    
    public ProcessInstance startProcess(WorkflowInfo workflowInfo) {
		String workflowPath = "..\\send-to-topic\\src\\main\\resources"+workflowInfo.getFilePath();
		log.info(">>>>>>>>>>> basePath: "+workflowPath);
		return processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("userRegistrationProcess")
                .build());
    }
}
