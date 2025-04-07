package ch.bzz.sqli_demo;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/safe")
public class UserController {

    @Autowired
    private DataSource dataSource;

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

        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();

             //prep
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            //exec
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("users", users);

        System.out.println("Users: " + users);

        if (users.size() == 1) {
            return "welcome";
        } else {
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                model.addAttribute("error", "Password wrong or user not found");
            }
            return "index";
        }
    }
}


