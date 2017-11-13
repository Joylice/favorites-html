package com.example.favorites.web.filter;

import com.example.favorites.web.domain.JwtUser;
import com.example.favorites.web.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuiyy on 2017/11/8.
 */
public class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
//        List<String> authorities = new ArrayList<>();
//        authorities.add("ROLE_USER");
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getPassWord(),
                user.getEmail(),
                //mapToGrantedAuthorities(user.getRoles()),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
