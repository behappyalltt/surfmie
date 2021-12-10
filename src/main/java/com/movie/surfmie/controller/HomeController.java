package com.movie.surfmie.controller;

import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/join")
    public String join() {
        return "home/join";
    }

    @PostMapping("/join")
    public String join(MemberDto memberDto) {
        memberService.join(memberDto);

        return "redirect:/login";
    }

    // 접근 불가 페이지
    @GetMapping("/denied")
    public String denied() {
        return "home/denied";
    }
}
