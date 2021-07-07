package com.example.workflow.subscriber;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.workflow.jms.publisher.JmsProducer;
import com.example.workflow.model.Actions;
import com.example.workflow.model.WorkflowInfo;
import com.example.workflow.services.ProcessService;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Log4j
public class JmsConsumer implements MessageListener {

	@Autowired
	private ProcessService processService;
	@Autowired
	private JmsProducer jmsProducer;
    @Override
    @JmsListener(destination = "${topic}",containerFactory = "jmsListenerContainerFactory")
	public void onMessage(Message message) {
        try{
        	log.debug(">>>>>JmsConsumer.onMessage(Message message) --> Start");
            ObjectMessage objectMessage = (ObjectMessage)message;
            WorkflowInfo workflowInfo = (WorkflowInfo)objectMessage.getObject();
            //do additional processing
            log.info(">>>>> Received Message from Topic: "+workflowInfo.toString());
            log.info(Actions.CREATE_AND_START_WORKFLOW.toString());
            if (workflowInfo.getAction().equals(Actions.CREATE_AND_START_WORKFLOW.toString())) {
            	String processDefinitionId = processService.startProcess(workflowInfo);
            	log.info(">>>>>> ProcessInstance: "+processDefinitionId);
            	//sent to  workflow middleware
            	workflowInfo.setAction(Actions.SEND_PROCESS_DEFINITION_ID.toString());
            	workflowInfo.setProcessDefinitionId(processDefinitionId);
            	jmsProducer.sendProcessDefinitionId(workflowInfo);
            }
        } catch(Exception e) {
        	log.info(">>>>>> Received Exception while processing message: "+ e);
        }
        
    }
}
