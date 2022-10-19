package edu.hanoi.jazz.controller;

import ch.qos.logback.classic.Logger;
import edu.hanoi.jazz.ContextStartEvenHandler;
import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.impl.GroupDAOImpl;
import edu.hanoi.jazz.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/nhom")
public class GroupController {
    @Autowired
    private GroupDAO groupDAO1;



    private  final  static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContextStartEvenHandler.class);

    @RequestMapping( value = "/them",method = RequestMethod.GET)
    ModelAndView addForm(){
     return (new ModelAndView("group.form","command", new Group()));
    }
    @RequestMapping( value = "/luu",method = RequestMethod.POST)
    ModelAndView luu(@Valid @ModelAttribute("command") Group group, BindingResult result
    ){
        if(result.hasErrors()){
            ModelAndView md = new ModelAndView("group.form","command",group);
            md.addObject("error",result);
            return md;
        }
        if(group.getId()>0){
            groupDAO1.update(group);
            LOGGER.info("Update new Group: " + group);

        }else {
            LOGGER.info("Add new Group: " + group);
            groupDAO1.insert(group);
        }
        return new ModelAndView("group.form");
    }
    @RequestMapping( value = "/danh-sach",method = RequestMethod.GET)
    ModelAndView list(@RequestParam(value = "q", required = false) String pattern){
        ModelAndView mv = new ModelAndView("group.list");
        mv.addObject("groups",groupDAO1.list(pattern));
        return mv;
    }
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        if (id == null) return new ModelAndView("redirect:/nhom/danh-sach");
        groupDAO1.delete(id);
        return new ModelAndView("redirect:/nhom/danh-sach");
    }
    @RequestMapping(value = "/update")
    public ModelAndView update(@RequestParam(value = "id" , required = true) Integer id){
        Group group = groupDAO1.getGroupById(id);
        if (group == null) return new ModelAndView("redirect:/group/list");
        return new ModelAndView("group.form","command",group);
    }
}
