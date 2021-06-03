package com.example.workflow.subscriber;

import lombok.extern.log4j.Log4j;

//import org.activiti.api.process.model.ProcessInstance;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.workflow.model.Actions;
import com.example.workflow.model.BroadcastMessage;
import com.example.workflow.model.WorkflowInfo;
//import com.example.workflow.services.ProcessService;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Log4j
public class JmsConsumer implements MessageListener {

	//@Autowired
	//private ProcessService processService;
	
    @Override
    @JmsListener(destination = "${topic}",containerFactory = "jmsListenerContainerFactory")
	public void onMessage(Message message) {
        try{
        	log.debug(">>>>>JmsConsumer.onMessage(Message message) --> Start");
            ObjectMessage objectMessage = (ObjectMessage)message;
            BroadcastMessage broadcastMessage = (BroadcastMessage)objectMessage.getObject();
            //do additional processing
            log.info(">>>>> Received Message from Topic: "+
            		broadcastMessage.toString()
            );
            log.info(Actions.CREATE_AND_START_WORKFLOW.toString());
            if (broadcastMessage.getWorkflowInfo().getAction().equals(Actions.CREATE_AND_START_WORKFLOW.toString())) {
            	//ProcessInstance processInstance = processService.startProcess(broadcastMessage.getWorkflowInfo());
            	//log.info(">>>>>> ProcessInstance: "+processInstance);
            }
        } catch(Exception e) {
        	log.info(">>>>>> Received Exception while processing message: "+ e);
        }
    }
}
