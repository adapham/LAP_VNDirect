package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.Message;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @MessageMapping("/user")
    @SendTo("/topic/chat")
    public Message add(User user , Message username){
        if (username.getContent()== null){
            String status = userDAO.insert(user);
            System.out.println(username.getContent());
            System.out.println("insert user : "+user.getUsername()+" - email "+user.getEmail()+"  save "+status + "successfull");
            return new Message("insert user "+user.getUsername()+" - email "+user.getEmail()+"  save "+status + "successfull");
        }else{
            userDAO.update(user);
            System.out.println(username.getContent());
            System.out.println("update user : "+user.getUsername()+" - email "+user.getEmail()+"  save " + "successfull");
            return new Message("update user "+user.getUsername()+" - email "+user.getEmail()+"  save "+ "successfull");
        }

    }

    @MessageMapping("/user/delete")
    @SendTo("/topic/chat")
    public Message delete(Message username , Principal principal){
        int status = userDAO.delete(username.getContent());
        System.out.println(username);
        if (status==1){
            return new Message(principal.getName()+" : DELETE User : "+username + " successful !");
        }else {
            return new Message(principal.getName()+" : DELETE User : "+username + " not successful !");
        }
    }

}
