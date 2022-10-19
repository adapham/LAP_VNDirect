package edu.hanoi.spring.dao;


import edu.hanoi.spring.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> list();
    public void addMore();
    public String insert(User user);
    public void update(User user);
    public int delete(String username);
    public User get(String username);

}
