package com.finalpjc.ott.model;

import com.finalpjc.ott.dto.ArticleRequestDto;
import com.finalpjc.ott.repository.mapping.UsernameMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(length = 12, nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @Lob
    private String picture;

    @Column(nullable = true)
    @Lob
    private String video;

    @Transient
    private String usernamePicture;

    @Transient
    private Long commentCount;

    @Transient
    private Long articleLikeItCount;

    @Transient
    private Boolean articleLikeItChecker;

    @Transient
    private final List<String> articleLikeItUserlist = new ArrayList<>();

    public void addUsernamePicture(String Picture) {
        this.usernamePicture = picture;
    }

    public void addComentCount(Long count) {
        this.commentCount = count;
    }

    public void addLikeItCount(Long count) {
        this.articleLikeItCount = count;
    }

    public void changeLikeItChecker(Boolean trueOrFalse) {
        this.articleLikeItChecker = trueOrFalse;
    }

    public void addLikeItUserList(UsernameMapping likeItUser) {
        this.articleLikeItUserlist.add(likeItUser.getUsername());
    }

    public Article(ArticleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.picture = requestDto.getPicture();
        this.video = requestDto.getVideo();

    }

    public void articleUpdate(ArticleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.picture = requestDto.getPicture();
        this.video = requestDto.getVideo();
    }


}
