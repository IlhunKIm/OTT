package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.CommentLikeItRequestDto;
import com.finalpjc.ott.model.CommentLikeIt;
import com.finalpjc.ott.repository.CommentLikeItRepository;
import com.finalpjc.ott.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class CommentLikeItService {

    private final CommentLikeItRepository commentLikeItRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Map<String, Boolean> commentLikeIt(CommentLikeItRequestDto requestDto) {
        Map<String, Boolean> commentLikeItMap = new HashMap<>();
        if(commentRepository.findById(requestDto.getCommentId()).isPresent()) {
            if(commentLikeItRepository.findByUsernameAndCommentId(requestDto.getUsername(), requestDto.getCommentId()).isPresent()) {
                commentLikeItRepository.deleteByUsernameAndCommentId(requestDto.getUsername(), requestDto.getCommentId());
            } else {
                commentLikeItRepository.save(new CommentLikeIt(requestDto));
                commentLikeItMap.put("commentLikeIt", true);
            }
        } else {
            throw new NullPointerException("Id가 " + requestDto.getCommentId() + "인 댓글이 존재하지 않습니다");

        }
        return commentLikeItMap;
    }

}
