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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/unsafe")
public class UserControllerNot {

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

        List<Users> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            if (username != null && password != null) {
                String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Users user = new Users();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("users", users);

        if (users.size() == 1) {
            return "welcome";
        } else {
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                model.addAttribute("error", "Password wrong or user not found");
            }
            return "indexNot";
        }
    }
}


//Username:' OR '1'='1
//password:' OR '1'='1

//Username:admin'--
//password:test