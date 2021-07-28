package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.ArticleLikeItRequestDto;
import com.finalpjc.ott.model.ArticleLikeIt;
import com.finalpjc.ott.repository.ArticleLikeItRepository;
import com.finalpjc.ott.service.ArticleLikeItService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ArticleLikeItController {

    private final ArticleLikeItService articleLikeItService;
    private final ArticleLikeItRepository articleLikeItRepository;

    @PostMapping("/user/article/likeIt") // 좋아요 클릭, 더블 클릭 시 해제
    public Map<String, Boolean> clickLike(@RequestBody ArticleLikeItRequestDto requestDto) {
        return articleLikeItService.articleLikeIt(requestDto);
    }

    @GetMapping("/user/article/likeit") // 좋아요 클릭한 사람 다 보여주기
    public List<ArticleLikeIt> likeItCheck() {
        return articleLikeItRepository.findAll();
    }


}
