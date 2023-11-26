package vn.edu.iuh.fit.www_week06_2210;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.www_week06_2210.backend.models.Post;
import vn.edu.iuh.fit.www_week06_2210.backend.models.PostComment;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostRepository;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class WwwWeek062210Application {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    private User user;
    private Post post1;
    public static void main(String[] args) {
        SpringApplication.run(WwwWeek062210Application.class, args);
    }
//    @Bean
////    CommandLineRunner initData() {
////        return args -> {
////            Random rnd = new Random();
////            for (int i = 1; i < 200; i++) {
////              User user=new User(
////                        "First #"+i,"Middle #"+i,"Last #"+i,
////                        rnd.nextLong(1111111111L, 9999999999L) + "",
////                        "email" +i + "@gmail.com","pw000"+i, Instant.now(),Instant.now(),"Text #"+i,"Intro # "+i);
////                userRepository.save(user);
////                System.out.println("Added: " + user);
////            }
////        };
////     }
//    @Bean
//    CommandLineRunner initDataPost() {
//
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 200; i++) {
//                Post post=new Post(
//                        new User(1L),new Post(1L),"title #"+i,"metaTitle #"+i,"summary #"+i,true,Instant.now(),Instant.now(),Instant.now(),"content #"+i
//                );
//                postRepository.save(post);
//                System.out.println("Added: " +post);
//            }
//
//        };
//     }
//    @Bean
//    CommandLineRunner initDataPostComment() {
//
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 200; i++) {
//                PostComment postComment=new PostComment(new Post(1L),"title #"+i,new User(1L),true,Instant.now(),Instant.now(),"content # "+i);
//                postCommentRepository.save(postComment);
//                System.out.println("Added: " +postComment);
//            }
//
//        };
    //}

}
