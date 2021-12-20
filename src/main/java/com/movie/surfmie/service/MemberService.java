package com.movie.surfmie.service;

import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.entity.MemberEntity;
import com.movie.surfmie.config.Role;
import com.movie.surfmie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(email);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    public String join(MemberDto memberDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPasswd(encoder.encode(memberDto.getPasswd()));

        try {
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
            memberDto.setBirthDt(transFormat.parse(memberDto.getBirthDtStr()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            memberDto.setBirthDt(new Date());
        }

        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = transFormat.format(date);

        String[] emailId = memberDto.getEmail().split("@");
        if(emailId[0].length() > 6) {
            id += emailId[0].substring(0, 6);
        } else {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 6 - emailId[0].length();
            Random random = new Random();

            String generatedString = random.ints(leftLimit,rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            id += emailId[0] + generatedString;
        }

        id = memberRepository.save(MemberEntity.builder()
                .id(id)
                .email(memberDto.getEmail()).passwd(memberDto.getPasswd())
                .first_name(memberDto.getFirst_name()).last_name(memberDto.getLast_name())
                .gender(memberDto.getGender()).birthDt(memberDto.getBirthDt()).phone(memberDto.getPhone()).nickname(memberDto.getNickname())
                .code_id("M04").is_enabled(true).createDt(new Date()).updateDt(new Date())
                .build()).getId();

        if(id != null) {
            // terms_agree 처리
        }

        return id;
    }
}