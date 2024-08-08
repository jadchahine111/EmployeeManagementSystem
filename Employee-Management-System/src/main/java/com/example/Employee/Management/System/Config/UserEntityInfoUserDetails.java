package com.example.Employee.Management.System.Config;

import com.example.Employee.Management.System.Permission.Permission;
import com.example.Employee.Management.System.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityInfoUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> roles;

    public UserEntityInfoUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        // Ensure this path does not return null
        Permission permission = user.getEmployee() != null
                ? user.getEmployee().getJob() != null
                ? user.getEmployee().getJob().getPermission()
                : null
                : null;

        // If permission is null, handle it gracefully
        if (permission != null) {
            this.roles = List.of(permission)
                    .stream()
                    .map(p -> new SimpleGrantedAuthority("ROLE_" + p.getType()))
                    .collect(Collectors.toList());
        } else {
            this.roles = new ArrayList<>();
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
