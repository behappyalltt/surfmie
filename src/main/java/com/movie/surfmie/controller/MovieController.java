package com.movie.surfmie.controller;

import com.movie.surfmie.config.UserCustom;
import com.movie.surfmie.dto.MovieDetailResponseDto;
import com.movie.surfmie.entity.BookmarkEntity;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import com.movie.surfmie.service.BookmarkService;
import com.movie.surfmie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private MovieService movieService;

    // home
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("paddington", movieService.getMovieDetail("116149"));
        model.addAttribute("movie_upcoming", movieService.getUpcoming());
        model.addAttribute("movie_today", movieService.getTrending("/all/day"));
        model.addAttribute("movie_week", movieService.getTrending("/all/week"));
        return "movie/main";
    }

    // 영화 상세
    @GetMapping("/movie/detail")
    public String detail(Model model, String id, @AuthenticationPrincipal UserCustom user) {
        model.addAttribute("movie", movieService.getMovieDetail(id));
        model.addAttribute("credits", movieService.getMovieCredits(id));
        if(user != null) model.addAttribute("bookmark", bookmarkService.getBookmark(id, user.getId()));
        return "movie/detail";
    }

    // My List(북마크)
    @GetMapping("/movie/mylist")
    public String mylist(Model model, @AuthenticationPrincipal UserCustom user) {
        List<MovieDetailResponseDto> movies = new ArrayList<>();
        List<BookmarkEntity> bookmarks = bookmarkService.getBookmarks(user.getId());
        for (BookmarkEntity bookmark: bookmarks)
        {
            BookmarkPrimaryKey bookmarkPK = bookmark.getBookmarkPrimaryKey();
            movies.add(movieService.getMovieDetail(bookmarkPK.getMovie_id()));
        }

        model.addAttribute("movies", movies);
        return "movie/mylist";
    }
}
