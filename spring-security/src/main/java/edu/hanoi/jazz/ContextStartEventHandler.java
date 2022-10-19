package edu.hanoi.jazz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);
    @Autowired private DataSource dataSource;
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        try{
//            createTable("HN_GROUP","create table HN_GROUP(id bigint primary key" +
//                    " generated always as identity (start with 1, increment by 1)," +
//                    " name varchar(100))");
            createTable("HN_USER","create table HN_USER(username VARCHAR(100) primary key," +
                    "password varchar(100)," +
                    "email varchar(100)," +
                    "age Integer," +
                    "groupId bigint," +
                    "CONSTRAINT GROUP_FK FOREIGN KEY (groupId) REFERENCES HN_GROUP(id))");
        }catch (Exception e){
            LOGGER.error(e,e);
        }
    }

    private void createTable(String name, String script) throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, name, null);
        if(rs.next()) {
            LOGGER.info("Table "+rs.getString("TABLE_NAME")+" already exists!");
            return;
        }
        dataSource.getConnection().createStatement().executeUpdate(script);

    }
}
