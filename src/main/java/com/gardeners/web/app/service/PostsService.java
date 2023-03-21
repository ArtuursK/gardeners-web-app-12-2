package com.gardeners.web.app.service;

import com.gardeners.web.app.entities.Post;
import com.gardeners.web.app.mapper.GardenerPostsMapper;
import com.gardeners.web.app.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Post getPostById(long postId) {
        return jdbcTemplate.query(
                "select * from post " +
                        "where post.post_id = ?;", new PostMapper(), postId).get(0);
    }

    public List<Post> getAllPosts() {
        return jdbcTemplate.query(
                "select gardener.username, gardener.avatar_image_url, post.post_id, post.description, post.datetime, post.imageurl " +
                        "from post, gardener " +
                        "where gardener.gardener_id = post.gardener_id " +
                        "order by post.post_id desc;", new GardenerPostsMapper());
    }

    //https://www.digitalocean.com/community/tutorials/spring-jdbctemplate-example
    public List<Post> getAllPostsByUsername(String username) {
        return jdbcTemplate.query(
                "select gardener.username, gardener.avatar_image_url, post.post_id, post.description, post.datetime, post.imageurl " +
                        "from post, gardener " +
                        "where gardener.gardener_id = post.gardener_id and " +
                        "gardener.username = ?" +
                        "order by post.post_id desc;", new GardenerPostsMapper(), username);
    }

    public boolean updatePostByPostId(String postDescription, long gardenerId, String originalFilename, long postId) {
        return jdbcTemplate.update("update post set description = ?, gardener_id = ?, imageurl = ? " +
                "where post.post_id = ?;", postDescription, gardenerId, originalFilename, postId) > 0;
    }

    public boolean saveNewPost(String postDescription, long gardenerId, String originalFilename) {
        return jdbcTemplate.update("insert into post (description, gardener_id, imageurl) " +
                "values(?,?,?)", postDescription, gardenerId, originalFilename) > 0;
    }

    public boolean deletePostById(long postId) {
        return jdbcTemplate.update("delete from post where post_id = ?;", postId) > 0;
    }

    //attempt to sql inject
    public List<Post> getAllInjPosts(String username) {
        return jdbcTemplate.query(
                "select * from post, gardener where gardener.gardener_id = post.gardener_id and gardener.username = '" + username + "';", new GardenerPostsMapper());
    }
}
