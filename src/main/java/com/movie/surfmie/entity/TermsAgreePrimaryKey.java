package com.movie.surfmie.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsAgreePrimaryKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String member_id;
    private String terms_id;
}
