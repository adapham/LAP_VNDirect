package edu.hanoi.spring.dao.impl;

import edu.hanoi.spring.dao.GroupDAO;
import edu.hanoi.spring.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insertRandom() {

    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        }finally {
            session.close();
        }
    }
}
