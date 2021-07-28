package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.RecommentLikeIt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommentLikeItRepository extends JpaRepository<RecommentLikeIt, Long> {

    Long countByRecommentId(Long articleId);

    void deleteByUsernameAndRecommentId(String username, Long recommentId);

    Optional<RecommentLikeIt> findByUsernameAndRecommentId(String username, Long recommentId);

}
