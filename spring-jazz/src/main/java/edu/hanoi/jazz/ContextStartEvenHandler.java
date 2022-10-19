package edu.hanoi.jazz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContextStartEvenHandler implements ApplicationListener<ContextStartedEvent> {
    private  final  static Logger LOGGER = Logger.getLogger(ContextStartEvenHandler.class);
    @Autowired
    private DataSource dataSource;
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        LOGGER.info("context start application "+ dataSource);
        try {
//            createTable("HN_GROUP","CREATE TABLE HN_GROUP ( id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) , name VARCHAR(1000))");
            createTable("HN_USER","create table HN_USER(username VARCHAR(100) primary key," +
                    "password varchar(100)," +
                    "email varchar(100)," +
                    "age Integer," +
                    "groupId bigint," +
                    "CONSTRAINT GROUP_FK FOREIGN KEY (groupId) REFERENCES HN_GROUP(id))");
        } catch (SQLException e) {
            LOGGER.error(e,e);
        }
    }
    private void createTable(String name, String script) throws  SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
//        ResultSet rs = dbmd.getTables(null, null, "HN_GROUP", null);
        ResultSet rs1 = dbmd.getTables(null, null, "HN_USER", null);
        if (rs1.next()) {
//            LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exists !");
            LOGGER.info("Table " + rs1.getString("TABLE_NAME") + " already exists !");
            return;
        }
        dataSource.getConnection().createStatement().executeUpdate(script);
    }

}
