package com.movie.surfmie.service;

import com.movie.surfmie.dto.TermsAgreeDto;
import com.movie.surfmie.entity.TermsAgreeEntity;
import com.movie.surfmie.entity.TermsAgreePrimaryKey;
import com.movie.surfmie.repository.TermsAgreeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TermsAgreeService {
    @Autowired
    private TermsAgreeRepository termsAgreeRepository;

    // 개인정보 동의 기록
    public TermsAgreePrimaryKey agreeTerms(TermsAgreeDto termsAgreeDto) {
        return termsAgreeRepository.save(TermsAgreeEntity.builder()
                        .termsAgreePrimaryKey(new TermsAgreePrimaryKey(termsAgreeDto.getMember_id(), termsAgreeDto.getTerms_id()))
                        .is_agree(termsAgreeDto.is_agree()).agreeDt(new Date()).etc(null)
                .build()
        ).getTermsAgreePrimaryKey();
    }
}