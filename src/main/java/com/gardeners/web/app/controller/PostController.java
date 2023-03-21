package com.gardeners.web.app.controller;

import com.gardeners.web.app.entities.Post;
import com.gardeners.web.app.mapper.PostMapper;
import com.gardeners.web.app.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostsService postsService;

    @RequestMapping(value ="/allposts")
    public String getAllPosts(Authentication authentication, Model model) {
        //Access the user that is logged in and set it in model attribute
        String loggedInUserName = authentication.getName();
        model.addAttribute("username", loggedInUserName);

        //get all posts for this user
        List<Post> allPosts = postsService.getAllPosts();
        model.addAttribute("userIsAdmin", false);
        model.addAttribute("userAvatar", "");
        model.addAttribute("caller", "allposts");
        model.addAttribute("posts", allPosts);
        return "allposts";
    }




}
