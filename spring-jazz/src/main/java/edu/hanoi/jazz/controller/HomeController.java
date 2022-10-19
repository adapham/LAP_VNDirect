package edu.hanoi.jazz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","Hello");
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("nguoi-dung")
    ModelAndView forUser(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","Hello ,THis is Page!");
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("dang-nhap")
    ModelAndView login(@RequestParam(value ="error",required = false) String error){
        ModelAndView mv = new ModelAndView("login");
        if(error!=null)
        mv.addObject("error","error");
        return mv;
    }
}
