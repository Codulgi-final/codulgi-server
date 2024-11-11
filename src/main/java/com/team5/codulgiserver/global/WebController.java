package com.team5.codulgiserver.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/today/dashboard")
    public String index() {
        return "today-dashboard";
    }
}
