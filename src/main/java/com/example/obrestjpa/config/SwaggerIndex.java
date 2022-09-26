package com.example.obrestjpa.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerIndex {
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui/index.html";
    }
}
