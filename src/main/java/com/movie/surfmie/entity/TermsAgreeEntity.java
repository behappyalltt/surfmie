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
@Entity(name = "terms_agree")
public class TermsAgreeEntity {
    // 복합키(Primary key가 여러개의 컬럼으로 구성)
    @EmbeddedId
    private TermsAgreePrimaryKey termsAgreePrimaryKey;

    private boolean is_agree;

    @Column(name = "agreeDt")
    private Date agreeDt;

    private String etc;
}
