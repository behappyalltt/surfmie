package com.movie.surfmie.service;

import com.movie.surfmie.dto.BookmarkDto;
import com.movie.surfmie.entity.BookmarkEntity;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import com.movie.surfmie.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    // My List(북마크) 추가
    public BookmarkPrimaryKey addBookmark(BookmarkDto bookmarkDto) {
        return bookmarkRepository.save(BookmarkEntity.builder()
                        .bookmarkPrimaryKey(new BookmarkPrimaryKey(bookmarkDto.getMember_id(), bookmarkDto.getMovie_id()))
                        .createDt(new Date())
                .build()
        ).getBookmarkPrimaryKey();
    }

    // My List(북마크) 삭제
    public void deleteBookmark(BookmarkDto bookmarkDto) {
        bookmarkRepository.deleteById(new BookmarkPrimaryKey(bookmarkDto.getMember_id(), bookmarkDto.getMovie_id()));
    }

    // My List(북마크) 추가 여부
    public boolean getBookmark(String movie_id, String member_id) {
        BookmarkPrimaryKey bookmarkPrimaryKey = new BookmarkPrimaryKey(member_id, movie_id);
        Optional<BookmarkEntity> bookmarkEntity = bookmarkRepository.findById(bookmarkPrimaryKey);
        if(bookmarkEntity.isEmpty()) return false;
        else return true;
    }

    // My List(북마크) 추가 목록 가져오기
    public List<BookmarkEntity> getBookmarks(String member_id) {
        return bookmarkRepository.findAllByMember_id(member_id);
    }
}