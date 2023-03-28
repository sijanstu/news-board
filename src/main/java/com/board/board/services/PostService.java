package com.board.board.services;

import com.board.board.beans.Post;
import com.board.board.beans.User;
import com.board.board.exceptions.ResourceNotFoundException;
import com.board.board.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> getAllPosts() {
        //return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("PostModel", "id", id));
    }

    public Post createPost(Post post) {
        User user = userService.getUserById(post.getUser().getId());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }
}
