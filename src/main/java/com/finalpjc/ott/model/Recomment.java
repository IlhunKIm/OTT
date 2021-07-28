package com.finalpjc.ott.model;


import com.finalpjc.ott.dto.RecommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Recomment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long commentId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Transient
    private Long recommentLikeItCount;

    @Transient
    private Boolean recommentLikeItChecker;

    public Recomment(RecommentRequestDto requestDto) {
        this.commentId = requestDto.getCommentId();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void addRecommentLikeItCount(Long count) {

        this.recommentLikeItCount = count;
    }

    public void changeLikeItChecker(Boolean trueOrFalse) {

        this.recommentLikeItChecker = trueOrFalse;
    }


    public void recommentUpdate(RecommentRequestDto requestDto) {
        this.commentId = requestDto.getCommentId();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }





}
