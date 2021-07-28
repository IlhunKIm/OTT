package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.CommentLikeItRequestDto;
import com.finalpjc.ott.model.CommentLikeIt;
import com.finalpjc.ott.repository.CommentLikeItRepository;
import com.finalpjc.ott.service.CommentLikeItService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentLikeItController {

    private final CommentLikeItRepository commentLikeItRepository;
    private final CommentLikeItService commentLikeItService;

    @PostMapping("/user/comment/likeIt") // 좋아요 클릭, 더블 클릭 시 해제
    public Map<String, Boolean> clickLike(@RequestBody CommentLikeItRequestDto requestDto) {
        return commentLikeItService.commentLikeIt(requestDto);
    }

    @GetMapping("/user/comment/likeIt") // 좋아요 클릭한 사람 다 보여주기
    public List<CommentLikeIt> readCommentLikeIt() {
        return commentLikeItRepository.findAll();
    }



}
