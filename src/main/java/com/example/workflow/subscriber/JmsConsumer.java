package com.example.workflow.subscriber;

import lombok.extern.log4j.Log4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.workflow.model.Employee;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Log4j
public class JmsConsumer implements MessageListener {


    @Override
    @JmsListener(destination = "${active-mq.topic}",containerFactory = "jmsListenerContainerFactory")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Employee employee = (Employee)objectMessage.getObject();
            //do additional processing
            log.info(">>>>> Received Message from Topic: "+
            employee.toString());
        } catch(Exception e) {
        	log.info(">>>>>> Received Exception while processing message: "+ e);
        }

    }
}
