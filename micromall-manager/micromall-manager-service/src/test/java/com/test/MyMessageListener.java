package com.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 〈〉
 *
 * @author LewJay
 * @create 2018/6/14 21:23
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.err.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
