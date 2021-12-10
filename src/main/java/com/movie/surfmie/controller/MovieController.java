package com.movie.surfmie.controller;

import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "movie/main";
    }

    @GetMapping("/movie/new")
    public String newmovie() {
        return "movie/new";
    }

    @GetMapping("/movie/popular")
    public String popular() {
        return "movie/popular";
    }
}
