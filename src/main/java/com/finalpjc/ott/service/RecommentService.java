package com.finalpjc.ott.service;


import com.finalpjc.ott.dto.RecommentRequestDto;
import com.finalpjc.ott.model.Recomment;
import com.finalpjc.ott.repository.RecommentLikeItRepository;
import com.finalpjc.ott.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommentService {

    private final RecommentRepository recommentRepository;
    private final RecommentLikeItRepository recommentLikeItRepository;


    @Transactional
    public void createRecomment(RecommentRequestDto requestDto) {
        Recomment recomment = new Recomment(requestDto);
        recommentRepository.save(recomment);
    }

    @Transactional
    public void updateRecomment(Long id, RecommentRequestDto requestDto) {
        Recomment recomment = recommentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("ID not found")
        );
        recomment.recommentUpdate(requestDto);
    }

    public List<Recomment> recommentLikeItCounter(List<Recomment> recommentList, String username) {
        for (Recomment value : recommentList) {
            Long recommentId = value.getId();
            Long likesCount = recommentLikeItRepository.countByRecommentId(recommentId);

            value.addRecommentLikeItCount(likesCount);

            value.changeLikeItChecker(recommentLikeItRepository.findByUsernameAndRecommentId(username, recommentId).isPresent());
        }
        return recommentList;
    }

    @Transactional
    public void deleteRecomment(String username, Long commentId) {
        recommentRepository.deleteByUsernameAndCommentId(username, commentId);
    }



}
