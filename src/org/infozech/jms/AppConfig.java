package org.infozech.jms;


import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import org.springframework.jms.listener.adapter.MessageListenerAdapter;

@Configuration

public class AppConfig {
	
	
	@Bean
	DefaultMessageListenerContainer defaultMessageListenerContainer (MessageListenerAdapter messageListenerAdp, 
			ConnectionFactory connectionFactory){
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setMessageListener(messageListenerAdp);
		defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
		defaultMessageListenerContainer.setDestinationName("mailbox-destination");
		defaultMessageListenerContainer.setConcurrency("2-5");
		return defaultMessageListenerContainer;
		
	}
}
