package com.example.workflow.jms.publisher;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.workflow.model.WorkflowInfo;


@Slf4j
@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${topic}")
    private String topic;

    public WorkflowInfo sendProcessDefinitionId(WorkflowInfo workflowInfo) throws JmsException{
        log.info(">>>>>>> Attempting Send message to Topic: "+ topic);
        log.info(">>>>>>> processDefinitionId: "+ workflowInfo.getProcessDefinitionId());
        jmsTemplate.convertAndSend(topic, workflowInfo);
        log.info(">>>>>>> wonrkflowInfo with processDefinitionId: '"+ workflowInfo.getProcessDefinitionId()+"' sent to topic");
        return workflowInfo;
    }

}
