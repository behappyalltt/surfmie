package com.movie.surfmie.controller;

import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyPageController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/mypage")
    public String login() {
        return "mypage/mypage";
    }
}
