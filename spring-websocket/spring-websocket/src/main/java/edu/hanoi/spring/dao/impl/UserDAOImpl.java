package edu.hanoi.spring.dao.impl;


import edu.hanoi.spring.dao.UserDAO;
import edu.hanoi.spring.dao.factory.UserFactory;
import edu.hanoi.spring.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().openSession();
        try {
            Query query = session.createQuery("from User order by age desc");
            return (List<User>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void addMore() {
        Session session = sessionFactory.getObject().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (int i = 0; i < 5; i++) {
                session.save(UserFactory.generate(i));
            }
            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            LOGGER.error(e, e);
        } finally {
            session.close();
        }
    }

    @Override
    public String insert(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            Serializable value = session.save(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Save user " + user.getUsername() + " done!" + value);
            return value.toString();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getObject().openSession();
        try {
            session.getTransaction().begin();
            session.update(user);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Save user " + user.getUsername() + " done!");
        }finally {
            session.close();
        }
    }

    @Override
    public int delete(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            User user = session.get(User.class, username);
            if (user == null) return 0;
            session.getTransaction().begin();
            session.delete(user);
            session.flush();
            session.getTransaction().commit();
            return 1;
        }finally {
            session.close();
        }
    }

    @Override
    public User get(String username) {
        Session session = sessionFactory.getObject().openSession();
        try {
            return session.get(User.class, username);
        } finally {
            session.close();
        }
    }
}
