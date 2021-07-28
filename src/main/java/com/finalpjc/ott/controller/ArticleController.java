package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.ArticleRequestDto;
import com.finalpjc.ott.model.Article;
import com.finalpjc.ott.repository.ArticleRepository;
import com.finalpjc.ott.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    @PostMapping("/user/article") //게시글 작성
    public void createArticle(@RequestBody ArticleRequestDto requestDto) {
        articleService.createArticle(requestDto);
    }

    @GetMapping("/user/all-article/{username}/{page}/{size}")//전체 게시글 조회
    public Page<Article> getAllArticleWithSize(@PathVariable String username, @PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Article> articleList = articleRepository.findAllByOrderByCreatedAtDesc(pageable);
        return articleService.getPagedArticleList(articleList, username);
    }

    @GetMapping("/user/my-article/{username}/{page}/{size}")
    public Page<Article> getArticleWithSize(@PathVariable String username, @PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articleList = articleRepository.findAllByUsernameOrderByCreatedAtAsc(username, pageable);
        return articleService.getPagedArticleList(articleList, username);
    }

    @GetMapping("/user/article/{articleId}")
    public Article getArticleByArticleId(@PathVariable Long articleId) {
        return articleService.getArticleByArticleId(articleId);
    }

    @PutMapping("/user/article/{articleId}")
    public void updateArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDto requestDto) {
        articleService.updateArticle(articleId, requestDto);
    }

    @DeleteMapping("/user/article/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

}
