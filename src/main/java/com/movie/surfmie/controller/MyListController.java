package com.movie.surfmie.controller;

import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyListController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/mylist")
    public String login() {
        return "mylist/mylist";
    }
}
