package edu.hanoi.jazz.dao.impl;

import edu.hanoi.jazz.dao.GroupDAO;
import edu.hanoi.jazz.dao.model.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component("groupDAO")
public class GroupDAOImpl implements GroupDAO {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void insert(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try {
            Transaction tran = session.beginTransaction();
            session.save(group);
            session.flush();
            tran.commit();
            System.out.println("Save group " + group.getName() + " done!");
        } finally {
            session.close();
        }
//        System.out.println(sessionFactory+ ":Insert group "+group);
    }

    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery(" from Group");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getObject().openSession();
        Group group = session.get(Group.class, id);
        if (group == null) return;
        try {
            Transaction tran = session.beginTransaction();
            session.delete(group);
            session.flush();
            tran.commit();
            System.out.println("Delete group " + group.getName() + " successful!");
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Group group) {
        Session session = sessionFactory.getObject().openSession();
        try{
        group = (Group) session.merge(group);
        Transaction tran = session.beginTransaction();
        session.save(group);
        session.flush();
        tran.commit();
        System.out.println("update group "+group.getName()+"successful!");
        }finally {
            session.close();
        }

    }
    @Override
    public Group getGroup(int id) {
        for(Group group: list()){
            if(group.getId() == id){
                return group;
            }
        }
        return null;
    }

    @Override
    public List<Group> listbyGroup(String group) {
        Session session = sessionFactory.getObject().openSession();
        Query query = session.createQuery(" from Group G where G.name LIKE '%"+group+"%'");
        try {
            return (List<Group>) query.list();
        } finally {
            session.close();
        }
    }

}
