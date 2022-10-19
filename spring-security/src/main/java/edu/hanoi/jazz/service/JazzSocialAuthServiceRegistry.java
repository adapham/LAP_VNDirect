package edu.hanoi.jazz.service;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

public class JazzSocialAuthServiceRegistry extends SocialAuthenticationServiceRegistry {
    private List<SocialAuthenticationService<?>> authenticationServices;

    public JazzSocialAuthServiceRegistry(List<SocialAuthenticationService<?>> authenticationServices) {
        System.out.println("JazzSocialAuthServiceRegistry");
        this.authenticationServices = authenticationServices;
    }
    @PostConstruct
    public void init() {
//        String[] fieldsToMap = {
//                "id", "about", "age_range", "bio", "birthday", "context", "cover", "currency", "devices", "education", "email", "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type","is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format","political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other","sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "viewer_can_send_gift","website", "work"
//        };
//
//        Field field = null;
//        try {
//            field = Class.forName("org.springframework.social.facebook.api.UserOperations").
//                    getDeclaredField("PROFILE_FIELDS");
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        field.setAccessible(true);
//
//        Field modifiers = null;
//        try {
//            modifiers = field.getClass().getDeclaredField("modifiers");
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        modifiers.setAccessible(true);
//        try {
//            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            field.set(null, fieldsToMap);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
        if(authenticationServices == null) return;
        System.out.println("int");
        authenticationServices.forEach(auth -> super.addAuthenticationService(auth));
    }



}
