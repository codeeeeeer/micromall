package com.micromall.search.activemq;

import com.micromall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

/**
 * 〈the item add listener of search part, in order to update search content〉
 *
 * @author LewJay
 * @create 2018/6/16 10:45
 */
public class ItemAddListener implements MessageListener {
    @Autowired
    private SearchService searchService;
    @Override
    public void onMessage(Message message) {
        try {
            TimeUnit.SECONDS.sleep(1L);
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                    String idStr = textMessage.getText();
                    Long id = Long.parseLong(idStr);
                    searchService.addIndexById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
