package org.ie.mizdooni.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

@Entity
@DiscriminatorValue("client")
public class ClientUserModel extends UserModel {
    public ClientUserModel() {
        super();
    }

    @OneToMany(mappedBy = "clientUsername")
    private List<ReserveTableModel> clientReservations = new ArrayList<>();

    @Override
    public String getRole() {
        return "client";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole()));
    }
}
