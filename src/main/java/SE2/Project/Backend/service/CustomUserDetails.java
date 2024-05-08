package SE2.Project.Backend.service;

import SE2.Project.Backend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the roles of the user as GrantedAuthority collection
        // You can implement this method based on how roles are stored in your User model
        // For example, if your User model has a field for roles, you can convert them to GrantedAuthority objects
        String role = user.getRole();

        // Create a GrantedAuthority object based on the user's role
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        // Return a collection containing the single GrantedAuthority object
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        // Return the password of the user
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Return the username of the user
        return user.getUsername();
    }
//    @Override
//    public String getRole() {
//        // Return the username of the user
//        return user.getRole();
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Or implement your logic here
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Or implement your logic here
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Or implement your logic here
    }

    @Override
    public boolean isEnabled() {
        return true; // Or implement your logic here
    }
}

