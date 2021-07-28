package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Long countByArticleId(Long articleId);
    List<Comment> findAllByUsernameAndArticleId(String username, Long articleId);
    void deleteByUsernameAndArticleId(String username, Long articleId);
    List<Comment> findAllByArticleId(Long articleId);
    void deleteAllByArticleId(Long articleId);

}
