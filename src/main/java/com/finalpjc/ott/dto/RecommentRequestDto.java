package com.finalpjc.ott.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class RecommentRequestDto {

    private Long commentId;

    private String username;

    private String contents;

}
