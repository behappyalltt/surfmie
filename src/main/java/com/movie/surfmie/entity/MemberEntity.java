package com.movie.surfmie.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberEntity implements UserDetails {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String email;
    private String passwd;
    private String first_name;
    private String last_name;
    private String gender;

    @Column(name = "birthDt")
    private Date birthDt;

    private String phone;
    private String nickname;
    private String code_id;
    private boolean is_enabled;

    @Column(name = "createDt")
    private Date createDt;

    @Column(name = "updateDt")
    private Date updateDt;

    private String etc;

    // 사용자의 권한을 콜렉션 형태로 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : "".split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return passwd;
    }

    // username 반환 (unique한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 계정 만료 여부 반환
    // 만료 -> false
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    // 만료 -> false
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // password 만료 여부 반환
    // 만료 -> false
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    // 사용 불가 -> false
    @Override
    public boolean isEnabled() {
        return true;
    }
}
