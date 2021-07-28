package com.finalpjc.ott.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class ArticleLikeItRequestDto {

    private Long id;

    private String username;

    private Long articleId;


}
