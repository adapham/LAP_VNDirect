package edu.hanoi.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController {
    //page 12: luu lai truy cap vao file su dung thu vien log4j
    private final static Logger logger = Logger.getLogger(String.valueOf(HomeController.class));

    @RequestMapping("/")
    ModelAndView home(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", "Hello Java Clazz");
        logger.info("Da truy cap vao day");
        return mv;
    }
    @RequestMapping("/login")
    ModelAndView login(@RequestParam(value = "error", required = false)String error){
        ModelAndView mv = new ModelAndView("login");
        if(error!=null){
            mv.addObject("error","Sai ten hoac mat khau!");
            return mv;
        }
//        mv.addObject("message","hello User");
        return mv;
    }
    @RequestMapping("/user")
    ModelAndView forUser(){
        ModelAndView mv = new ModelAndView("index");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("message","hello User"+auth.getName());
        return mv;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/";
    }
}