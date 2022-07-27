package com.bologna.kafkaTest.service;

import com.bologna.kafkaTest.model.Messenger;

/**
 *
 * @author acbologna
 */
public interface MessengerService {

    void producerMessenger(Messenger messenger, Boolean async);
    
}
