package edu.java.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloClazzController {

//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView printMessage(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//        mv.addObject("message","Hello Java Clazz");
//        return mv;
//    }

    @RequestMapping(value ="welcome",method = RequestMethod.GET)
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("../clazz");
        mv.addObject("name"," LE DUC MINH ");
        return mv;
    }
    @RequestMapping(value ="site",method = RequestMethod.GET)
    public String redirect(){
        return "redirect:http://moom.vn";
    }

    @ResponseBody
    @RequestMapping(value = "data",method = RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE)
    public String raw(){
        return "Xin Chao moi nguoi";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView printMessage(@RequestParam(value = "data", required = false)String message){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message",message);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView printMessage1(@RequestBody(required = false)String message){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("message",message);
        return mv;
    }

}
