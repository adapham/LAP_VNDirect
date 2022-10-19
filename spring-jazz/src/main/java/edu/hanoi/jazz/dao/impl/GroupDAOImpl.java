package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.ContextStartEvenHandler;
import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
    private  final  static Logger LOGGER = Logger.getLogger(ContextStartEvenHandler.class);
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Override
    public void insert(Group group) {
        System.out.println(sessionFactoryBean+": Insert group "+ group);
        Session session = sessionFactoryBean.getObject().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(group);
            session.flush();
            transaction.commit();
//            session.getTransaction().begin();
            System.out.println(session.getSession());
            LOGGER.info("Save group " + group.getName()+" done! " );
        }finally {
            session.close();
        }
    }

    @Override
    public Group getGroupById(Integer id) {
        Session session = sessionFactoryBean.getObject().openSession();
        Group group = session.get(Group.class, id);
        return group;
    }

    @Override
    public List<Group> list(String pattern) {
        Session session = sessionFactoryBean.getObject().openSession();
        try{
        if(pattern == null || pattern.length() < 1){
            Query query = session.createQuery("from Group");
            return (List<Group>) query.list();
        }
//        Criteria criteria = session.createCriteria(Group.class);
//        criteria.add(Restrictions.like("name","%"+pattern+"%", MatchMode.ANYWHERE));
//        return new ArrayList<>(criteria.list());
            Query query = session.createQuery("from Group  where  name like :studentName");
            query.setParameter("studentName","%"+pattern+"%");
            return (List<Group>) query.list();
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactoryBean.getObject().openSession();
        group = (Group) session.merge(group);
        session.getTransaction().begin();
        session.save(group);
        session.flush();
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Update group "+group.getName()+" done!");
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactoryBean.getObject().openSession();
        Group group = session.get(Group.class, id);
        if (group == null) return;
        try {
            session.getTransaction().begin();
            session.delete(group);
            session.flush();
            session.getTransaction().commit();
            LOGGER.info("Delete group " + group.getName()+" successfully!");
        }finally {
            session.close();
        }

    }
}
