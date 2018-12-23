package com.spring.boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "admin")
public class Admin {
    @Id
    private ObjectId id;
    private String fullname;
    private String username;
    private String password;
    private Date createAt;

    public Admin(ObjectId id, String fullname, String username, String password, Date createAt) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.createAt = createAt;
    }


    public Admin() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
