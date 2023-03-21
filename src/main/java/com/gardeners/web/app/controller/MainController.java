package com.gardeners.web.app.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * entry endpoint into app
     * Note: May be redirected to login if not logged in
     * @return allposts page
     */
    @RequestMapping(value ="/")
    public String webAppRoot() {
        return "redirect:/allposts";
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
