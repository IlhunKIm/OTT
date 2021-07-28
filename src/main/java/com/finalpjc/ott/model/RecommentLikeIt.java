package com.finalpjc.ott.model;

import com.finalpjc.ott.dto.RecommentLikeItRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class RecommentLikeIt extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long recommentId;

    public RecommentLikeIt(RecommentLikeItRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.recommentId = requestDto.getRecommentId();
    }

}
