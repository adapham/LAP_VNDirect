package edu.hanoi.jazz.dao.impl;



import edu.hanoi.jazz.model.User;

import java.util.Random;

public class UserFactory {
    public static User generate(int index) {
        User user = new User();
        Random random = new Random();
        user.setUsername("username-random"+index);
        user.setPassword("password"+random.nextInt() +1);
        user.setEmail("ten "+random.nextInt()+1+" @gmail.com");
        user.setAge(random.nextInt(100)+1);
        user.setGroupId(1);
        return user;
    }
}
