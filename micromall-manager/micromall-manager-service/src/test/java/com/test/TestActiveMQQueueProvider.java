package com.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 〈〉
 *
 * @author LewJay
 * @create 2018/6/14 20:42
 */
public class TestActiveMQQueueProvider {
    @Test
    public void testProvider(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jms.xml");
        JmsTemplate jmsTemplate = context.getBean("jmsTemplate", JmsTemplate.class);
        ActiveMQQueue queueDestination = context.getBean("queueDestination", ActiveMQQueue.class);
        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Hello Spring Active MQ 2 !!");
            }
        });
    }
}
