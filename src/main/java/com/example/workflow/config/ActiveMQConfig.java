package com.example.workflow.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import lombok.extern.log4j.Log4j;

import javax.jms.ConnectionFactory;
@Log4j
@Configuration
public class ActiveMQConfig {

	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory,
	                                                           DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    log.info("config listener");
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    factory.setPubSubDomain(true);
	    factory.setSubscriptionDurable(true);
        factory.setClientId("brokerClientId911");
	    return factory;
	}
}
