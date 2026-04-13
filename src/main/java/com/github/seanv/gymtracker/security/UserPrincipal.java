package com.github.seanv.gymtracker.security;

import com.github.seanv.gymtracker.entities.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/** No Spring Stereotype is needed as this short-lived object that is just responsible for wrapping a User instance
    for the duration of a request. We will be creating new insatnces of this class ourselves in UserDetailsService
 */

public class UserPrincipal implements UserDetails {

    private User user;


    public UserPrincipal(User user){
        this.user = user;
    }

    // this means: Give me something that extends GrantedAuthority interface - SimpleGrantedAuthority does
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    //usually make use of column in User entity that flags whether account is enabled or not
    @Override
    public boolean isEnabled(){
        return true;
    }

    //Used when there are 5 failed attempts to log in - you then lock the account
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
}
