package com.movie.surfmie.repository;

import com.movie.surfmie.entity.BookmarkEntity;
import com.movie.surfmie.entity.BookmarkPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, BookmarkPrimaryKey> {
    @Query(value="select * from bookmark where member_id = :member_id", nativeQuery = true)
    List<BookmarkEntity> findAllByMember_id(@Param("member_id") String member_id);
}
