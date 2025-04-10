package ch.bzz.sqli_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jpa")
public class UserControllerJPA {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam MultiValueMap<String, String> values, Model model) {
        String username = values.getFirst("username");
        String password = values.getFirst("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (username == null || password == null) {
            model.addAttribute("error", "Username or password is missing");
            return "indexJpa";
        }

        User user = userRepository.findByUsernameAndPassword(username.toLowerCase(), password);

        if (user != null) {
            System.out.println("User: " + user);
            return "welcome";
        } else {
            model.addAttribute("error", "Password wrong or user not found");
            return "indexJpa";
        }
    }
}