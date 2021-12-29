package com.movie.surfmie.service;

import com.movie.surfmie.dto.MemberDto;
import com.movie.surfmie.dto.TermsAgreeDto;
import com.movie.surfmie.config.UserCustom;
import com.movie.surfmie.entity.MemberEntity;
import com.movie.surfmie.config.Role;
import com.movie.surfmie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.UniqueKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Autowired
    private TermsAgreeService termsAgreeService;

    // 회원 정보 가져오기
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(email);

        if(userEntityWrapper.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new UserCustom(userEntity.getEmail(), userEntity.getPassword(),
                userEntity.getId(), userEntity.getNickname(), authorities);
    }

    // 회원가입(spring security)
    public String join(MemberDto memberDto) {
        if(validateDuplicateEmail(memberDto.getEmail()) == false) {
            throw new DuplicateKeyException(memberDto.getEmail());
        } else if(validateDuplicateNickname(memberDto.getNickname()) == false) {
            throw new DuplicateKeyException(memberDto.getNickname());
        }

        // 비밀번호 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPasswd(encoder.encode(memberDto.getPasswd()));

        // 생년월일 String -> Date 변환
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

        // id 생성
        String[] emailId = memberDto.getEmail().split("@");
        if (emailId[0].length() > 6) {
            id += emailId[0].substring(0, 6);
        } else {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 6 - emailId[0].length();
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
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

        // terms_agree 처리 (개인정보 동의 여부)
        if (id != null) {
            TermsAgreeDto termsAgreeDto = new TermsAgreeDto();
            termsAgreeDto.setMember_id(id);
            termsAgreeDto.setTerms_id("TERMS01211221");
            termsAgreeDto.set_agree(true);
            termsAgreeService.agreeTerms(termsAgreeDto);
        }

        return id;
    }

    // 이메일 중복 체크
    public boolean validateDuplicateEmail(String email) {
        Optional<MemberEntity> member = memberRepository.findByEmail(email);
        if(member.isEmpty()) return true;
        else return false;
    }

    // 별명(닉네임) 중복 체크 
    public boolean validateDuplicateNickname(String nickname) {
        Optional<MemberEntity> member = memberRepository.findByNickname(nickname);
        if(member.isEmpty()) return true;
        else return false;
    }

    // 회원 정보 가져오기
    public MemberDto getMemberInfo(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email).get();
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setFirst_name(memberEntity.getFirst_name());
        memberDto.setLast_name(memberEntity.getLast_name());
        memberDto.setGender(memberEntity.getGender());
        memberDto.setBirthDtStr((new SimpleDateFormat("YYYY-MM-DD")).format(memberEntity.getBirthDt()));
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setNickname(memberEntity.getNickname());
        memberDto.setCode_id(memberEntity.getCode_id());
        memberDto.set_enable(memberEntity.is_enabled());
        memberDto.setCreateDt(memberEntity.getCreateDt());
        memberDto.setUpdateDt(memberEntity.getUpdateDt());
        memberDto.setEtc(memberEntity.getEtc());

        return memberDto;
    }
}