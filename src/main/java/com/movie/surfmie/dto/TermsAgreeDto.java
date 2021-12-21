package com.movie.surfmie.dto;

import lombok.*;

@Data
public class TermsAgreeDto {
    private String member_id;
    private String terms_id;
    private boolean is_agree;
    private String agreeDt;
    private String etc;
}
