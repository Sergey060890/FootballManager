package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static project.controllers.Constants.LOGIN;

@Controller
public class LoginController {
    /**
     * Login controller
     */

    @GetMapping("/login")
    String login() {
        return LOGIN;
    }
}
