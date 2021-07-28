package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.RecommentLikeItRequestDto;
import com.finalpjc.ott.model.RecommentLikeIt;
import com.finalpjc.ott.repository.RecommentLikeItRepository;
import com.finalpjc.ott.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RecommentLikeItService {

    private final RecommentRepository recommentRepository;
    private final RecommentLikeItRepository recommentLikeItRepository;
    private final RecommentLikeIt recommentLikeIt;

    @Transactional
    public Map<String, Boolean> recommentLikeIt(RecommentLikeItRequestDto requestDto) {
        Map<String, Boolean> recommentLikeItMap = new HashMap<>();
        if (recommentRepository.findById(requestDto.getRecommentId()).isPresent()) {
            if (recommentLikeItRepository.findByUsernameAndRecommentId(requestDto.getUsername(), requestDto.getRecommentId()).isPresent()) {
                recommentLikeItRepository.deleteByUsernameAndRecommentId(requestDto.getUsername(), requestDto.getRecommentId());
                recommentLikeItMap.put("commentLikeIt", false);
            } else {
                recommentLikeItRepository.save(new RecommentLikeIt(requestDto));

            }
        } else {
            throw new NullPointerException("Id가 " + requestDto.getRecommentId() + "인 댓글이 존재하지 않습니다");
        }
        return recommentLikeItMap;
    }

}
