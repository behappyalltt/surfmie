package com.movie.surfmie.controller;

import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @Autowired
    private MemberService memberService;

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "home/join";
    }

    // 회원가입 처리
    @PostMapping("/join")
    public String join(MemberDto memberDto) {
        try {
            memberService.join(memberDto);
            return "redirect:/login";
        } catch (DuplicateKeyException e) {
            return "redirect:/join?error";
        } catch (Exception e) {
            return "redirect:/join?error";
        }
    }

    // 접근 불가 페이지
    @GetMapping("/denied")
    public String denied() {
        return "home/denied";
    }

    // 로그아웃 페이지
    // Get
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/";
    }
}
