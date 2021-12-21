package com.movie.surfmie.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "bookmark")
public class BookmarkEntity {
    @EmbeddedId
    private BookmarkPrimaryKey bookmarkPrimaryKey;

    @Column(name = "createDt")
    private Date createDt;
}
