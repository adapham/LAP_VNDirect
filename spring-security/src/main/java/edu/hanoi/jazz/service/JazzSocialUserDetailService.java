package edu.hanoi.jazz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service("socialUserDetailService")
public class JazzSocialUserDetailService implements SocialUserDetailsService {
    @Autowired
    @Qualifier(value = "jazzUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public SocialUserDetails loadUserByUserId(final String userId) throws UsernameNotFoundException {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        if (user == null) {
            throw new SocialAuthenticationException("No user mapped with social user " + userId);
        }
        System.out.println("loadUserByUserId");
        return new SocialUser(user.getUsername(), user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), user.getAuthorities());
    }
}
