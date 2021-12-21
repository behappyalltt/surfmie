package com.movie.surfmie.repository;

import com.movie.surfmie.entity.BookmarkEntity;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, BookmarkPrimaryKey> {
}
