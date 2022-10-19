package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.dao.impl.StudentDAOImpl;
import edu.java.spring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


@Controller
public class StudentController {

    @Autowired(required = true)
    //@Qualifier("studentDAOImpl")
    private StudentDAO studentDAO;

    public void setStudentDAO(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public ModelAndView add() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.form");
//        return mv;
        return new ModelAndView("student.form", "command", new Student());
    }

//    @RequestMapping(value = "/student/save",method = RequestMethod.POST)
//    public ModelAndView save(@RequestParam(value = "name",required = false)String name,@RequestParam(value = "age", required = false) String age){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.view");
//        Student student;
//        try {
//            student = new Student(name,Integer.parseInt(age));
//        }catch (Exception e) {
//            student = new Student(name,0);
//        }
//        mv.addObject("student",student);
//        return mv;
//    }

    @RequestMapping(value = "/student/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("command") @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("student.form", "command", student);
            mv.addObject("errors", result);
            mv.setViewName("student.form");
            return mv;
        }

        if (student.getId() > 0) {
            studentDAO.update(student);
        } else {
            studentDAO.insert(student);
        }
//            ModelAndView mv = new ModelAndView();
//            mv.setViewName("student.view");
//            mv.addObject("student",student);
        return new ModelAndView("redirect:/student/list");

    }

//    @RequestMapping(value = "/student/save1", method = RequestMethod.POST)
//    public ModelAndView save1(@ModelAttribute("command") @Valid Student student, BindingResult result) {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test");
//        mv.addObject("result", result);
//
//        mv.addObject("student", student);
//        Student student1 = new Student("s", 1);
//        System.out.println(student1);
//        return mv;
//    }

//    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
//    public ModelAndView listStudent() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.list");
//        mv.addObject("students", studentDAO.list());
//        return mv;
//    }

    @RequestMapping(value = "/student/list", method = RequestMethod.GET)
    public ModelAndView listStudent(@RequestParam(value = "q" , required = false) String query) {
        if (query == null){
            query="";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student.list");
        mv.addObject("students", studentDAO.searchByName(query));
        mv.addObject("q",query);
        return mv;
    }



    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(value = "id") String id) {
        studentDAO.delete(Integer.parseInt(id));
        return "redirect:/student/list";
    }

    @RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id) {
        Student student = studentDAO.get(Integer.parseInt(id));
        return new ModelAndView("../student.form", "command", student);
    }

    @RequestMapping(value = "/student/edit1/{id}", method = RequestMethod.GET)
    public ModelAndView edit1(@PathVariable(value = "id") String id) {
        Student student = studentDAO.get(Integer.parseInt(id));
        ModelAndView mv = new ModelAndView("../StudentForm", "command", student);
        mv.addObject("student_id",id);
        return mv;
    }

    @RequestMapping(value = "/student/edit/save",method = RequestMethod.POST)
    public String saveEdit(){
        return "forward:/student/save";
    }

    @RequestMapping(value = "/student/json/{id}",method = RequestMethod.GET)
    public @ResponseBody Student viewJson(@PathVariable(value = "id") String id){
        return studentDAO.get(Integer.parseInt(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "redirect:/student/list1";
    }
    @RequestMapping(value = "/student/list1", method = RequestMethod.GET)
    public ModelAndView listStudent1(@RequestParam(value = "q" , required = false) String query) {
        if (query == null){
            query="";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("StudentList");
        mv.addObject("students", studentDAO.searchByName(query));
        mv.addObject("q",query);
        return mv;
    }

    @RequestMapping(value = "/student/avatar/save",method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file")MultipartFile file,String id,HttpServletRequest request){
        if (file.isEmpty()) return "../student.error";
        Path avatarFile = getImageFile(request,id);
        try {
            Files.write(avatarFile,file.getBytes(), StandardOpenOption.CREATE);
            byte[] bytes = file.getBytes();
            System.out.println("found ---->"+bytes.length);
            return "redirect:/student/list1";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Path getImageFile(HttpServletRequest request, String id){
        ServletContext servletContext = request.getSession().getServletContext();
        String diskPath = servletContext.getRealPath("/");
        File folder = new File(diskPath+File.separator+"avatar"+File.separator);
        folder.mkdirs();
        return (new File(folder,id+".jpg")).toPath();
    }
    @RequestMapping(value = "student/avatar/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "id")String id, HttpServletRequest request){
        try {
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            if (id != null){
                Path avatarPath = getImageFile(request,id);
                if (Files.exists(avatarPath)) byteOutput.write(Files.readAllBytes(avatarPath));
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(byteOutput.toByteArray(),headers, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
