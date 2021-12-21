package com.movie.surfmie.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "terms")
public class TermsEntity {
    @Id
    private String id;
    private String terms_name;

    @Column(name = "applyDt")
    private Date applyDt;

    private String terms_contents;
    private boolean is_required;
    private boolean is_enabled;
    private String etc;
}
