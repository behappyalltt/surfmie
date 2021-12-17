package com.movie.surfmie.controller;

import com.movie.surfmie.service.MemberService;
import com.movie.surfmie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("movie_upcoming", movieService.getUpcoming());
        model.addAttribute("movie_today", movieService.getTrending("/all/day"));
        model.addAttribute("movie_week", movieService.getTrending("/all/week"));
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
