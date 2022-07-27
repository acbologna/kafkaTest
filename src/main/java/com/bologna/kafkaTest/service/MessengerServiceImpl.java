package com.bologna.kafkaTest.service;

import com.bologna.kafkaTest.model.Messenger;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

/**
 *
 * @author acbologna
 */
@Service
public class MessengerServiceImpl implements MessengerService {

    private Properties propertiesConfig() {
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return p;
    }

    public void producerMessenger(Messenger messenger, Boolean async) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(propertiesConfig());
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(messenger.getTopic(), messenger.getMessenge());
        producer.send(producerRecord);

        if (!async) {
            producer.flush();
            producer.close();
        }
    }

}
