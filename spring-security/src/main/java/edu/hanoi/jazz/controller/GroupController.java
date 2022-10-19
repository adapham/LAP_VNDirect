package edu.hanoi.jazz.controller;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "group")
public class GroupController {
    @Autowired
    private GroupDAO groupDAO;

    //Add group
    @RequestMapping(value = "add", method = RequestMethod.GET)
    ModelAndView addForm() {
        return new ModelAndView("group.form", "command", new Group());
    }

    //Save groupp
    @RequestMapping(value = "save", method = RequestMethod.POST)
    ModelAndView addNew(@Valid @ModelAttribute("command") Group group, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView("group.form", "command", group);
            model.addObject("errors", result);
            return model;
        }
        if (group.getId() > 0) {
            groupDAO.update(group);
            System.out.println("Update group--------------->" + group.getName());
        } else {
            groupDAO.insert(group);
            System.out.println("add new group--------------->" + group.getName());

        }

        return new ModelAndView("redirect:/group/list");
    }

    //List group from table
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value = "q", required = false) String pattern) {
        ModelAndView mv = new ModelAndView("group.list");
        if (pattern == null) {
            mv.addObject("groups", groupDAO.list());
        } else {
            mv.addObject("groups", groupDAO.listbyGroup(pattern));
        }
        return mv;
    }

    //Delete group
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Integer id) {
        if (id == null) return new ModelAndView("redirect:/group/list");
        groupDAO.delete(id);
        System.out.println(id);
        return new ModelAndView("redirect:/group/list");
    }

    //Update group
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView updateForm(@RequestParam(value = "id", required = true) Integer id) {
        Group group = groupDAO.getGroup(id);
        if (group == null) return new ModelAndView("redirect:/group/list");
        return new ModelAndView("group.form", "command", group);
    }

}
