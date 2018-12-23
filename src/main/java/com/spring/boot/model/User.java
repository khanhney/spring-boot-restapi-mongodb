package com.spring.boot.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
public class User {
    @Id
    private ObjectId _id;
    private String fullname;
    private String username;
    private String password;
    private ObjectId userCreate;
    private Date createAt;
    private int status;

    public User(ObjectId _id, String fullname, String username, String password, ObjectId userCreate, Date createAt, int status) {
        this._id = _id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.userCreate = userCreate;
        this.createAt = createAt;
        this.status = status;
    }
    public User(){}

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public ObjectId getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(ObjectId userCreate) {
        this.userCreate = userCreate;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
