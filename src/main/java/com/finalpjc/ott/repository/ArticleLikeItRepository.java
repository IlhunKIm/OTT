package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.ArticleLikeIt;
import com.finalpjc.ott.repository.mapping.UsernameMapping;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeItRepository {
    Long countByArticleId(Long articleId);
    void deleteByUsernameAndArticleId(String username, Long articleId);
    Optional<ArticleLikeIt> findByUsernameAndArticleId(String username, Long articleId);
    List<UsernameMapping> findAllByArticleId(Long articleId);

}
