package com.movie.surfmie.controller;

import com.movie.surfmie.config.UserCustom;
import com.movie.surfmie.dto.BookmarkDto;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import com.movie.surfmie.service.BookmarkService;
import com.movie.surfmie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private MemberService memberService;

    // My List(북마크) 추가
    @GetMapping("/movie/addBookmark")
    public String addBookmark(String movie_id, @AuthenticationPrincipal UserCustom user) {
        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setMovie_id(movie_id);
        bookmarkDto.setMember_id(user.getId());
        BookmarkPrimaryKey bookmarkPrimaryKey = bookmarkService.addBookmark(bookmarkDto);

        if(bookmarkPrimaryKey == null) return "fail";
        else return "success";
    }

    // My List(북마크) 삭제
    @GetMapping("/movie/deleteBookmark")
    public String deleteBookmark(String movie_id, @AuthenticationPrincipal UserCustom user) {
        BookmarkDto bookmarkDto = new BookmarkDto();
        bookmarkDto.setMovie_id(movie_id);
        bookmarkDto.setMember_id(user.getId());
        bookmarkService.deleteBookmark(bookmarkDto);

        return "success";
    }

    // 이메일 중복 체크
    @GetMapping("/member/validateDuplicateEmail")
    public boolean validateDuplicateEmail(String email) {
        return memberService.validateDuplicateEmail(email);
    }

    // 닉네임(별명) 중복 체크
    @GetMapping("/member/validateDuplicateNickname")
    public boolean validateDuplicateNickname(String nickname) {
        return memberService.validateDuplicateNickname(nickname);
    }
}
