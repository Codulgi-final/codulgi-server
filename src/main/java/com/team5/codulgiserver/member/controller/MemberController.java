package com.team5.codulgiserver.member.controller;

import com.team5.codulgiserver.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private MemberService memberService;

    @GetMapping("/register")
    public String join() {
        return "register";
    }
}
