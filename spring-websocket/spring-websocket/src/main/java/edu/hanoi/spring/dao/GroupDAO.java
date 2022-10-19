package edu.hanoi.spring.dao;


import edu.hanoi.spring.model.Group;

import java.util.List;

public interface GroupDAO {
    public void insertRandom();
    public List<Group> list();
}
