package com.finalpjc.ott.model;


import com.finalpjc.ott.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String articleId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Transient
    private Long recommentCount;

    @Transient
    private Long commentLikeItCount;

    @Transient
    private boolean commentLikeItChecker;

    public Comment(CommentRequestDto requestDto) {
        this.articleId = requestDto.getArticleId();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void addRecommentCount(Long count) {

        this.recommentCount = count;
    }

    public void addCommentLikeItCount(Long count) {

        this.commentLikeItCount = count;
    }

    public void changeLikeItChecker(Boolean trueOrFalse) {
        this.commentLikeItChecker = trueOrFalse;
    }

    public void commentUpdate(CommentRequestDto requestDto) {

        this.contents = requestDto.getContents();
    }


}
