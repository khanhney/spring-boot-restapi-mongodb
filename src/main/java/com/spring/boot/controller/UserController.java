package com.spring.boot.controller;

import com.spring.boot.model.User;
import com.spring.boot.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("/**")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/add-user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody ObjectId addUser(User user) {
        userRepository.save(user);
        return user.get_id();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/info-user/{userId}")
    public User getInfoUser(@Valid @PathVariable ObjectId userId){
        return userRepository.findOne(String.valueOf(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update-status/{userId}/{status}")
    public String updateStatus(@Valid @PathVariable int status, @Valid @PathVariable ObjectId userId) {
        User user = userRepository.findOne(String.valueOf(userId));
        if (user != null) {
            user.setStatus(status);
            return "UPDATE STATUS SUCCESS";
        }else {
            return "USER NOT EXIST";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list-user")
    public Iterable<User> getListUser() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list-user-with-status/{status}")
    public Iterable<User> getListUserWithStatus(@PathVariable int status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status));
        Iterable<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/remove-user/{userId}")
    public String removeUser(@PathVariable ObjectId userId) {
        User userRemove = userRepository.findOne(String.valueOf(userId));
        if (userRemove!=null){
            userRepository.delete(userRemove);
            return "REMOVE USER SUCCESS";
        }else {
            return "REMOVE USER ERROR";
        }
    }

}
