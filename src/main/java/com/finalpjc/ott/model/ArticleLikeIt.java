package com.finalpjc.ott.model;


import com.finalpjc.ott.dto.ArticleLikeItRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class ArticleLikeIt extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long articleId;

    public ArticleLikeIt (ArticleLikeItRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.articleId = requestDto.getArticleId();

    }

}
