package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.RecommentLikeItRequestDto;
import com.finalpjc.ott.model.RecommentLikeIt;
import com.finalpjc.ott.repository.RecommentLikeItRepository;
import com.finalpjc.ott.service.RecommentLikeItService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RecommentLikeItControler {

    private final RecommentLikeItRepository recommentLikeItRepository;
    private final RecommentLikeItService recommentLikeItService;


    @PostMapping("/user/recomment/likeIt")
    public Map<String, Boolean> clickLike(@RequestBody RecommentLikeItRequestDto requestDto) {
        return recommentLikeItService.recommentLikeIt(requestDto);
    }

    @GetMapping("/user/recomment/likeIt")
    public List<RecommentLikeIt> readCommentLikes() {
        return recommentLikeItRepository.findAll();
    }

}
