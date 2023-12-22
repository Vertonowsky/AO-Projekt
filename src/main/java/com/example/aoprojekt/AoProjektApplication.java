package com.example.aoprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class AoProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoProjektApplication.class, args);
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
