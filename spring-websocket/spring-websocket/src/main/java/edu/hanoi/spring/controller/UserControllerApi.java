package edu.hanoi.spring.controller;

import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerApi {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/user/list",method = RequestMethod.GET , produces = "application/json")
    public List<User> list(){
        return userDAO.list();
    }

    @RequestMapping(value = "/user/get/{username}",method = RequestMethod.GET)
    public User get(@PathVariable(name = "username") String username){
        return userDAO.get(username);
    }
}
