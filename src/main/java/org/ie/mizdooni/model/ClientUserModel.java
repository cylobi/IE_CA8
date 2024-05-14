package org.ie.mizdooni.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
@DiscriminatorValue("client")
public class ClientUserModel extends UserModel{
    public ClientUserModel(){ super(); }

    @Override
    public String getRole() {
        return "client";
    }
}
