package org.ie.mizdooni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GlobalData {
    @Id
    public Integer id;
    public Boolean isUserLoginned;
    public String version;

    public GlobalData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsUserLoginned() {
        return isUserLoginned;
    }

    public void setIsUserLoginned(Boolean isUserLoginned) {
        this.isUserLoginned = isUserLoginned;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
