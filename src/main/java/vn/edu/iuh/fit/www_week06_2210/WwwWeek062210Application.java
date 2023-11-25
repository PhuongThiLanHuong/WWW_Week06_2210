package vn.edu.iuh.fit.www_week06_2210;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
    public static void main(String[] args) {
        SpringApplication.run(WwwWeek062210Application.class, args);
    }
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 200; i++) {
//              User user=new User(
//                        "First #"+i,"Middle #"+i,"Last #"+i,
//                        rnd.nextLong(1111111111L, 9999999999L) + "",
//                        "email" +i + "@gmail.com","pw000"+i, Instant.now(),Instant.now(),"Text #"+i,"Intro # "+i);
//                userRepository.save(user);
//                System.out.println("Added: " + user);
//            }
//        };
//     }

}
