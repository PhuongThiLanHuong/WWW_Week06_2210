package vn.edu.iuh.fit.www_week06_2210.fontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.www_week06_2210.backend.models.Post;
import vn.edu.iuh.fit.www_week06_2210.backend.models.PostComment;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;

    @GetMapping(value = {"/", "/index", "/posts"})
    public ModelAndView index(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, HttpSession session) {
        int pageNum = page.orElse(1);
        int sizeNum = size.orElse(10);

        PageRequest pageable = PageRequest.of(pageNum - 1, sizeNum, Sort.by("publishedAt"));

        Page<Post> posts = postRepository.findAllByPublished(true, pageable);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", session.getAttribute("user"));

        modelAndView.addObject("posts", posts);
        modelAndView.addObject("pages", IntStream.rangeClosed(1, posts.getTotalPages()).boxed().collect(Collectors.toList()));

        modelAndView.setViewName("index");

        return modelAndView;
    }
    @GetMapping("/show-add-form")
    public ModelAndView add(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        post.setAuthor(new User(1l));
        modelAndView.addObject("post", post);
        modelAndView.addObject("user", post.getAuthor());
        modelAndView.setViewName("post/addPost");
        return modelAndView;
    }
    @PostMapping("/post/add")
    public String addPOst(
            @ModelAttribute("post") Post post,
            BindingResult result, Model model) {
        postRepository.save(post);
        return "index";
    }
    @GetMapping(value = {"/post/{id}"})
    public ModelAndView postDetail(@PathVariable("id") String id, @RequestParam("page") Optional<Integer> page, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        Integer pageNum = page.orElse(1);

        try {
            Long idLong = Long.parseLong(id);
            Optional<Post> post = postRepository.findPostById(idLong);


            if (post.isPresent()) {
                PageRequest pageRequest = PageRequest.of(0, 5 * pageNum, Sort.by("createdAt").descending());

                Page<PostComment> comments = postCommentRepository.findAllByPostId(idLong, pageRequest);
                PostComment postComment = new PostComment();
                PostComment parenPostComment = new PostComment();

                modelAndView.addObject("post", post.get());
                modelAndView.addObject("comments", comments);
                modelAndView.addObject("postComment", postComment);
                modelAndView.addObject("parenPostComment", parenPostComment);
                modelAndView.addObject("user", session.getAttribute("user"));
                modelAndView.addObject("pageNext", comments.getSize() / 5 + 1);

                modelAndView.setViewName("post/detail");
            }
        } catch (Exception e) {
            modelAndView.setViewName("notFound");
        }

        return modelAndView;
    }
}
