package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.ArticleRequestDto;
import com.finalpjc.ott.model.Article;
import com.finalpjc.ott.model.Comment;
import com.finalpjc.ott.repository.ArticleLikeItRepository;
import com.finalpjc.ott.repository.ArticleRepository;
import com.finalpjc.ott.repository.CommentRepository;
import com.finalpjc.ott.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final ArticleLikeItRepository articleLikeItRepository;

   @Transactional
    public void createArticle(ArticleRequestDto articleRequestDto) {
       Article article = new Article(articleRequestDto);
       articleRepository.save(article);
   }

   @Transactional
    public void updateArticle(Long articleId, ArticleRequestDto articleRequestDto) {
       Article article = articleRepository.findById(articleId).orElseThrow(
               () -> new NullPointerException("ID not found")
       );
       article.articleUpdate(articleRequestDto);
   }

   public Page<Article> getPagedArticleList(Page<Article> articlelist, String username) {
       for (Article value : articlelist) {
           Long articleId = value.getId();
           List<Comment> commentList = commentRepository.findAllByArticleId(articleId);
           Long recommentCount = 0L;

           for (Comment comment : commentList) {
               recommentCount += recommentRepository.countByCommentId(comment.getId());
           }

           Long commentCount = commentRepository.countByArticleId(articleId);
           Long likescount = articleLikeItRepository.countByArticleId(articleId);

           value.addComentCount(commentCount + recommentCount);
           value.addLikeItCount(likescount);

           value.changeLikeItChecker(articleLikeItRepository.findByUsernameAndArticleId(username, articleId).isPresent());
           articleLikeItRepository.findAllByArticleId(articleId).forEach(value::addLikeItUserList);
       }
       return articlelist;

   }

    public Article getArticleByArticleId(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new NullPointerException("articleId not found")
        );
    }


    @Transactional
    public void deleteArticle(Long articleId){
       articleRepository.deleteById(articleId);
   }

}
