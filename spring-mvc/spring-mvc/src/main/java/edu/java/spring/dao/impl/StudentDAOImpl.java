package edu.java.spring.dao.impl;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO , DisposableBean {

    private static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);


    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @PostConstruct
    private void createTableIfNotExists() throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exists !");
            return;
        }
        jdbcTemplate.execute("CREATE TABLE STUDENT ( id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , name VARCHAR(1000) , age INTEGER )");
    }

    @Override
    public void insert(Student student) {
        jdbcTemplate.update("INSERT INTO STUDENT (name , age) VALUES ( ? , ? )" , student.getName(),student.getAge());
        LOGGER.info("Create Record Name = "+student.getName());
    }

    @Override
    public List<Student> list() {
        return this.jdbcTemplate.query("SELECT * FROM STUDENT ",new StudentRowMapper());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.execute("DELETE FROM STUDENT WHERE id = "+id);
    }

    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:derby:D:/LabSpringProject/spring-mvc/student_db;shutdown=true");
    }
    @Override
    public Student get(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = "+id,new StudentRowMapper());
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("UPDATE STUDENT SET name = ? , age = ? WHERE id = ? ",student.getName(),student.getAge(),student.getId());
    }

    @Override
    public List<Student> searchByName(String name) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE name LIKE '%"+name+"%'",new StudentRowMapper());
    }

    private final static class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) {
            try {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                return student;
            }catch (Exception e){
                LOGGER.error(e,e);
                return null;
            }

        }
    }
}
