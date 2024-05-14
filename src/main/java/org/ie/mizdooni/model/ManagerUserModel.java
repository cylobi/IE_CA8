package org.ie.mizdooni.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("manager")
public class ManagerUserModel extends UserModel {
    public ManagerUserModel(){ super(); }

    @Override
    public String getRole() {
        return "manager";
    }
}
