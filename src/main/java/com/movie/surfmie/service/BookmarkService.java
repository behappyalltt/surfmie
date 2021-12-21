package com.movie.surfmie.service;

import com.movie.surfmie.dto.BookmarkDto;
import com.movie.surfmie.entity.BookmarkEntity;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import com.movie.surfmie.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public BookmarkPrimaryKey addBookmark(BookmarkDto bookmarkDto) {
        return bookmarkRepository.save(BookmarkEntity.builder()
                        .bookmarkPrimaryKey(new BookmarkPrimaryKey(bookmarkDto.getMember_id(), bookmarkDto.getMovie_id()))
                        .createDt(new Date())
                .build()
        ).getBookmarkPrimaryKey();
    }
}