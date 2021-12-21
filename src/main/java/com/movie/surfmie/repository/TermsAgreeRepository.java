package com.movie.surfmie.repository;

import com.movie.surfmie.entity.TermsAgreeEntity;
import com.movie.surfmie.entity.TermsAgreePrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsAgreeRepository extends JpaRepository<TermsAgreeEntity, TermsAgreePrimaryKey> {
}
