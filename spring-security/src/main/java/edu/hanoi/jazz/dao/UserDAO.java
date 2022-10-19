package edu.hanoi.jazz.dao;

import edu.hanoi.jazz.dao.model.Group;
import edu.hanoi.jazz.dao.model.User;

import java.util.List;

public interface UserDAO {
    public void insert(User user);
    public List<User> list(int group);
    public List<User> listall();
    public User get(String username);
    public void delete(String name);

    public List<User> listOlder();

    public int averageAge();
    public List<User> page(int page);
    public List<User> listUserByNativeSQL();

    public void addBatch();
}
