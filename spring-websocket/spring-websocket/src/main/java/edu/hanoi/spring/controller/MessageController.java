package edu.hanoi.spring.controller;

import edu.hanoi.spring.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/chat")
    public Message say(Message message, Principal principal) throws InterruptedException {
        System.out.println(" From client: "+principal.getName()+"----"+message.getContent());
        Thread.sleep(2000);
        return new Message(principal.  getName()+" : "+message.getContent()+" ! ");
    }
}
