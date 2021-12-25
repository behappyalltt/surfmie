package com.movie.surfmie.controller;

import com.movie.surfmie.config.UserCustom;
import com.movie.surfmie.service.BookmarkService;
import com.movie.surfmie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovieController {
    @Autowired
    private BookmarkService bookmarkService;

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

    @GetMapping("/movie/detail")
    public String detail(Model model, String id, @AuthenticationPrincipal UserCustom user) {
        model.addAttribute("movie", movieService.getMovieDetail(id));
        model.addAttribute("credits", movieService.getMovieCredits(id));
        if(user != null) model.addAttribute("bookmark", bookmarkService.getBookmark(id, user.getId()));
        return "movie/detail";
    }

    @GetMapping("/movie/mylist")
    public String mylist(Model model, @AuthenticationPrincipal UserCustom user) {
        model.addAttribute("bookmarks", bookmarkService.getBookmarks(user.getId()));
        return "movie/mylist";
    }
}
