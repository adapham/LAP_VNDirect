package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.model.Group;
import edu.hanoi.jazz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private UserDAO userDAO;
    //Add user
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        ModelAndView mv = new ModelAndView("user.form", "command", new User());
        mv.addObject("groups", toGroupMap(groupDAO.list(null)));
        return mv;

    }
    //Save User
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView addNew(@Valid @ModelAttribute("command") User user, BindingResult result) {
        if(result.hasErrors()) {
            ModelAndView mv = new ModelAndView("user.form","command",new User());
            mv.addObject("groups",toGroupMap(groupDAO.list(null)));
            mv.addObject("errors",result);
            return mv;
        }
        userDAO.insert(user);
        System.out.println("add new user------------->"+user);
        return new ModelAndView("redirect:/nhom/danh-sach");
    }
    //List User
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Integer group) {
        ModelAndView mv = new ModelAndView("user.list");
        mv.addObject("users",userDAO.list(group));
        return mv;
    }
    @RequestMapping(value = "list1",method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("user.list");
//        mv.addObject("users",userDAO.list());
        mv.addObject("users",userDAO.listall());
        mv.addObject("average",userDAO.averageAge());
//        mv.addObject("users",userDAO.page(1));
        return mv;
    }
    //Detail User
    @RequestMapping(value = "detail/{username}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable String username) {
        ModelAndView mv = new ModelAndView("user.detail");
        mv.addObject("users",userDAO.get(username));
        return mv;
    }
    //delete User
    @RequestMapping(value = "delete/{name}",method = RequestMethod.GET)
    public String delete(@PathVariable String name) {
        userDAO.delete(name);
        return "redirect:/user/list1";
    }
    //Add Random User
    @RequestMapping(value = "addrandom",method = RequestMethod.GET)
    public String addRandom() {
        userDAO.addBatch();
        return "redirect:/user/list1";
    }

    private Object toGroupMap(List<Group> groups) {
        Map<Integer, String> map = new HashMap<>();
        groups.forEach(group -> map.put(group.getId(), group.getName()));
        return map;
    }

}
