package com.movie.surfmie.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookmarkPrimaryKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String member_id;
    private String movie_id;
}
