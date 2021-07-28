package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.ArticleLikeItRequestDto;
import com.finalpjc.ott.model.ArticleLikeIt;
import com.finalpjc.ott.repository.ArticleLikeItRepository;
import com.finalpjc.ott.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ArticleLikeItService {

    private final ArticleRepository articleRepository;
    private final ArticleLikeItRepository articleLikeItRepository;
    private final ArticleLikeItRequestDto articleLikeItRequestDto;

    @Transactional
    public Map<String, Boolean> articleLikeIt(ArticleLikeItRequestDto requestDto) {
        Map<String, Boolean> articleLikeItMap = new HashMap<>();

        if (articleRepository.findById(requestDto.getArticleId()).isPresent()) {
            if (articleLikeItRepository.findByUsernameAndArticleId(requestDto.getUsername(), requestDto.getArticleId()).isPresent()) {
                articleLikeItRepository.deleteByUsernameAndArticleId(requestDto.getUsername(), requestDto.getArticleId());
                articleLikeItMap.put("articleLikeIt", false);

            } else {
                articleLikeItRepository.save(new ArticleLikeIt(requestDto));
            }
        } else {
            throw new NullPointerException("Id가 " + articleLikeItRequestDto.getArticleId() + "인 게시글이 존재하지 않습니다");
        }
        return articleLikeItMap;
    }

}
