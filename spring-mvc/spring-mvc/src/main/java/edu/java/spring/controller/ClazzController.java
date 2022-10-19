package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.entity.JavaClazz;
import edu.java.spring.entity.Student;
import edu.java.spring.utils.XSLTUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ClazzController {
    @Autowired(required = true)
    private StudentDAO studentDAO;

//    public void setStudentDAO(StudentDAO studentDAO) {
//        this.studentDAO = studentDAO;
//    }

    @RequestMapping(value = "/clazz/xml" , produces = {MediaType.APPLICATION_XML_VALUE})
    public @ResponseBody JavaClazz viewInXml() {
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "/clazz/json" , produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody JavaClazz viewInJson(){
        return new JavaClazz(studentDAO.list());
    }

    @RequestMapping(value = "/clazz/xslt")
    public ModelAndView viewXSLT(){
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("ClazzView");

        model.getModelMap().put("data", XSLTUtils.clazztoDOMSource(clazz));
        return model;
    }

    @RequestMapping(value = "/clazz/excel")
    public ModelAndView viewExcel(){
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("excelView");
        modelAndView.getModelMap().put("clazzObj",clazz);
        return modelAndView;
    }
    @RequestMapping(value = "/clazz/pdf", produces = {"application/pdf"})
    public ModelAndView viewPDF(){
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pdfView");
        modelAndView.getModelMap().put("clazzObj",clazz);
        return modelAndView;
    }
    @RequestMapping(value = "/clazz/report",produces = {"application/pdf"})
    public ModelAndView viewReport(){
        JRDataSource dataSource = new JRBeanCollectionDataSource(studentDAO.list());
        ModelAndView mv = new ModelAndView("pdfReport");
        mv.addObject("dataSource",dataSource);
        JRPdfExporter exporter = new JRPdfExporter();

        return mv;
    }
}
