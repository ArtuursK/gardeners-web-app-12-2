package com.gardeners.web.app;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/get-all-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        //return all posts from DB

        //rezultāti formātā:
        //post_id , gardener_id, description , datetime, username
        //rezultātus glabā sarakstā (Šajā gad. "List")
        //Katram ierakstam iekš List (katra rinda tabulā) ir datu tips "Post", ko izveidojām katras rindas glabāšanai
        List<Post> postList = jdbcTemplate.query(
                "select post.post_id, post.description, post.datetime, gardener.username " +
                        "from post, gardener " +
                        "where post.gardener_id = gardener.gardener_id;",
                new PostMapper());
        return ResponseEntity.ok().body(postList);
    }

    @RequestMapping(value = "/allposts")
    public String getAllPostsTh(Model model) {
        //return all posts from DB
        List<Post> postList = jdbcTemplate.query(
                "select post.post_id, post.description, post.datetime, gardener.username " +
                        "from post, gardener " +
                        "where post.gardener_id = gardener.gardener_id;",
                new PostMapper());
        model.addAttribute("posts", postList);
        return "index.html";
    }

    @RequestMapping(value = "/gardener/create")
    public String createGardener(Model model) {
        Gardener gardener = new Gardener();
        gardener.setUsername();
        gardener.setPassword(hashPassword(""));
        return "";
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
