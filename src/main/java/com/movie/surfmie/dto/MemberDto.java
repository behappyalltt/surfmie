package com.movie.surfmie.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDto {
    private String id;
    private String email;
    private String passwd;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birthDt;
    private String birthDtStr;
    private String phone;
    private String nickname;
    private String code_id;
    private boolean is_enable;
    private Date createDt;
    private Date updateDt;
    private String etc;
}
