package com.movie.surfmie.repository;

import com.movie.surfmie.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepository extends JpaRepository<TermsEntity, Long> {
}
