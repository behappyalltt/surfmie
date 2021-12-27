package com.movie.surfmie.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// Security 에서 사용되는 User에 파라미터 추가
@Getter
@Setter
//@Data (constructor 도중 에러가 발생하므로 사용하지 않음)
@ToString
public class UserCustom extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // 추가할 유저 정보
    private String id;
    private String nickname;

    public UserCustom(String username, String password, String id, String nickname, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}