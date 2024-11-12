package com.team5.codulgiserver.global.controller;

import com.team5.codulgiserver.domain.member.dto.MemberResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        MemberResponse loginUser = (MemberResponse) session.getAttribute("member");

        if (loginUser != null) {
            model.addAttribute("loginUser", loginUser);
        }

        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/today/dashboard")
    public String index() {
        return "today-dashboard";
    }

    @GetMapping("/posting")
    public String posting() {
        return "posting";
    }
}
