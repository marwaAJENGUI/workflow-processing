package com.example.workflow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;

import com.example.workflow.model.WorkflowInfo;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class ProcessService {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private FileService fileService;
    
    public ProcessInstance startProcess(WorkflowInfo workflowInfo) {
		log.debug(">>>>>>>>>>> ProcessRuntime -> : ProcessInstance("+workflowInfo.getName()+"_"+workflowInfo.getVersion()+"): start");
		fileService.copyXmlFile(workflowInfo.getFilePath(),workflowInfo.getDirPath());
		return processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey(workflowInfo.getName())
                .build());
    }
}
