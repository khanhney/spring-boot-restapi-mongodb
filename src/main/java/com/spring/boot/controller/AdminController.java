package com.spring.boot.controller;

import com.spring.boot.model.Admin;
import com.spring.boot.repository.AdminRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "/**")
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/add-admin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody String addAdmin(Admin admin){
        adminRepository.save(admin);
        return String.valueOf(admin.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/{adminID}")
    public Admin getInfo(@PathVariable ObjectId adminId) {
        Admin adminInfo = adminRepository.findOne(String.valueOf(adminId));
        return adminInfo;
    }
}
