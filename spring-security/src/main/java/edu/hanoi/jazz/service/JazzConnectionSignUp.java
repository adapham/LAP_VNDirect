package edu.hanoi.jazz.service;

import edu.hanoi.jazz.dao.UserDAO;
import edu.hanoi.jazz.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

public class JazzConnectionSignUp implements ConnectionSignUp {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private FBService fbService;

    @Override
    public String execute(final Connection<?> connection) {
        System.out.println("_+_+_+_+___________________________________");
//        String adminUser = facebook.userOperations().getUserProfile().getName();
//        UserProfile userProfile = connection.fetchUserProfile();
        UserProfile userProfile = fbService.getProfile("me", connection.createData().getAccessToken());
        System.out.println(userProfile.getEmail());
        System.out.println("--------->id " + userProfile.getId() + " name " + userProfile.getUsername());
        User user = userDAO.get(userProfile.getId());
        if (user != null) return user.getUsername();
        User user1 = new User();
        user1.setUsername(userProfile.getId());
        user1.setPassword("123");
        user1.setGroupId(501);
        user1.setEmail(userProfile.getEmail());
        user1.setAge(19);
        userDAO.insert(user1);
        return user1.getUsername();

    }
}
