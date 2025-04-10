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

        Users users = null;
        if (username != null && password != null) {
            users = userRepository.findByUsernameAndPassword(username, password);
        }

        System.out.println("User: " + users);

        if (users != null) {
            return "welcome";
        } else {
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                model.addAttribute("error", "Password wrong or user not found");
            }
            return "indexJpa";
        }
    }
}