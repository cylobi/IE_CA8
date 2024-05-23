package org.ie.mizdooni.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@DiscriminatorValue("manager")
public class ManagerUserModel extends UserModel {
    public ManagerUserModel() {
        super();
    }

    @Override
    public String getRole() {
        return "manager";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
    }
}
