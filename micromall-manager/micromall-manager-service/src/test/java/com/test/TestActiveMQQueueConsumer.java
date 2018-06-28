package com.test;

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
public class TestActiveMQQueueConsumer {
    @Test
    public void testProvider() throws Exception{
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jms.xml");
        System.in.read();
    }
}
