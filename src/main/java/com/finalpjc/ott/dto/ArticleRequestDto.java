package com.finalpjc.ott.dto;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
public class ArticleRequestDto {

    private String username;

    private String contents;

    private String picture;

    private String video;



}
