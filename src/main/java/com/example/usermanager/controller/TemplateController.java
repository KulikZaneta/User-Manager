package com.example.usermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/welcome")
    public String getLogout() {
        return "logout";
    }

    @GetMapping("/createUserAccount")
    public String showCreateUserAccountForm() {
        return "createUserAccount";
    }
}

