package org.ie.mizdooni.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("client")
public class ClientUserModel extends UserModel{
    public ClientUserModel(){ super(); }


    @OneToMany(mappedBy = "clientUsername")
    private List<ReserveTableModel> clientReservations = new ArrayList<>();

    @Override
    public String getRole() {
        return "client";
    }
}
