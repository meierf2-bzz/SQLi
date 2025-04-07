package ch.bzz.sqli_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        User user = userRepository.findByUsernameAndPassword(username, password);

        model.addAttribute("users", user);

        System.out.println("User: " + user);

        if (!user.equals(new User())) {
            return "welcome";
        } else {
            if (!username.equals("") && !password.equals("")) {
                model.addAttribute("error", "Password wrong or user not found");
            }
            return "indexJpa";
        }

    }
}


