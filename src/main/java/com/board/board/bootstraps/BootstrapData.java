package com.board.board.bootstraps;

import com.board.board.beans.Comment;
import com.board.board.beans.Post;
import com.board.board.beans.User;
import com.board.board.repositories.CommentRepository;
import com.board.board.repositories.PostRepository;
import com.board.board.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .name("Ram")
                .email("ram123@gmail.com")
                .avatar("https://i.pravatar.cc/")
                .build();
        user = userRepository.save(user);
        Post post = Post.builder()
                .title("First PostModel")
                .content("This is my first post")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        post = postRepository.save(post);
        Comment comment = Comment.builder()
                .content("This is my first comment")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .post(post)
                .build();
        comment = commentRepository.save(comment);

    }

}
