package com.bologna.kafkaTest.controller;

import com.bologna.kafkaTest.model.Messenger;
import com.bologna.kafkaTest.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author acbologna
 */
@RestController
@RequestMapping("/send")
public class MessengerControl {

    @Autowired
    private MessengerService messengerService;

    @PostMapping("/assync")
    public ResponseEntity sendMessengerAssync(@RequestBody Messenger messenger) {
        try {
            messengerService.producerMessenger(messenger, Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body("Enviada a mensagem");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no envio da mensagem");
        }
    }
    
    @PostMapping("/sync")
    public ResponseEntity sendMessengerSync(@RequestBody Messenger messenger) {
        try {
            messengerService.producerMessenger(messenger, Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body("Enviada a mensagem sincrona");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no envio da mensagem síncrona");
        }
    }

    @GetMapping("/teste")
    public ResponseEntity teste() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Funcionou");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no teste");
        }
    }

}
