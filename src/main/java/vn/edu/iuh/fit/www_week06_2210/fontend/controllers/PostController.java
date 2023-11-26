package vn.edu.iuh.fit.www_week06_2210.fontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.www_week06_2210.backend.models.Post;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;
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
    @GetMapping("post/add")
    public String showAddFrom(Model model,HttpSession session)
    {
        Object o=session.getAttribute("user");
        if(o==null)
            return "redirect:/login";
        User user=(User) o;
        List<Post> posts=postRepository.findAllByAuthorAndPublished(user,true);
        Post post=new Post();
        Post postParent=new Post();
        model.addAttribute("post",post);
        model.addAttribute("postParent",postParent);
        model.addAttribute("posts",posts);
        model.addAttribute("user",user);
        return "post/addPost";

    }
    @PostMapping("/post/addPost")
    public String addPost(@ModelAttribute("post") Post post,@ModelAttribute("postParent") Post postParent,HttpSession session)
    {
        Object o=session.getAttribute("user");
        if(o==null)
            return "redirect:/login";
        if(postParent.getId()!=null)
        {
            Optional<Post> parentPostOptional=postRepository.findById(postParent.getId());
            parentPostOptional.ifPresent(post::setParent);
        }
        User user= (User) o;
        post.setId(null);
        post.setAuthor(user);
        post.setPublished(true);
        post.setCreatedAt(Instant.now());
        post.setPublishedAt(Instant.now());
        if(postParent.getId()!=null)
        {
            Optional<Post> parentPostOptional=postRepository.findById(postParent.getId());
            parentPostOptional.ifPresent(post::setParent);
        }
        postRepository.save(post);
        return "redirect:/index";
    }
}
