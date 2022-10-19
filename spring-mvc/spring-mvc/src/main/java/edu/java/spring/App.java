package edu.java.spring;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.dao.impl.StudentDAOImpl;
import edu.java.spring.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hanoi-dispatcher-servlet.xml");
        StudentDAO jdbc = (StudentDAO) context.getBean("studentDAOImpl");
        jdbc.insert(new Student("MINH" , 19));
    }
}
