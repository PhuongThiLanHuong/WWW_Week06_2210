package vn.edu.iuh.fit.www_week06_2210.fontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.UserRepository;
import vn.edu.iuh.fit.www_week06_2210.backend.services.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;
    private UserService userService;
    @GetMapping("/{id}")
    private String getUser(@PathVariable("id") String id)
    {
        return "";
    }

    @GetMapping("/show-add-form")
    public String  showAddForm(@ModelAttribute User user, Model model)
    {
        user = new User();
        model.addAttribute("user", user);
        return "user/add-form";
    }
    @GetMapping("/show-login")
    public String showLogin(@ModelAttribute User user,Model model)
    {
        model.addAttribute("user",new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,Model model)
    {
        User user1=userService.login(user.getEmail(),user.getPasswordHash());
        model.addAttribute("userLogin",user1);
        return "index";
    }
}
