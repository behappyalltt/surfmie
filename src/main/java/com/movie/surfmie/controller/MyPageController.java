package com.movie.surfmie.controller;

import com.movie.surfmie.config.UserCustom;
import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.entity.MemberEntity;
import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyPageController {
    @Autowired
    private MemberService memberService;

    // 마이페이지
    @GetMapping("/mypage")
    public String mypage(Model model, @AuthenticationPrincipal UserCustom user) {
        model.addAttribute("member", memberService.getMemberInfo(user.getUsername()));
        return "mypage/mypage";
    }
}
