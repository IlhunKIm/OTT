package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.RecommentRequestDto;
import com.finalpjc.ott.model.Recomment;
import com.finalpjc.ott.repository.RecommentRepository;
import com.finalpjc.ott.service.RecommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RecommentController {

    private final RecommentRepository recommentRepository;
    private final RecommentService recommentService;

    @PostMapping("/user/recomment/")
    public void createRecomment(@RequestBody RecommentRequestDto requestDto) {
        recommentService.createRecomment(requestDto);
    }

    @GetMapping("/user/recomment/{username}/{commentId}/")
    public List<Recomment> readRecomment(@PathVariable String username, @PathVariable Long CommentId) {
        List<Recomment> recommentList = recommentRepository.findAllByUsernameAndCommentId(username, CommentId);
        return recommentService.recommentLikeItCounter(recommentList, username);
    }

    @PutMapping("/user/recomment/{recommentId}")
    public void updateComment(@PathVariable Long recommentId, @RequestBody RecommentRequestDto requestDto) {
        recommentService.updateRecomment(recommentId, requestDto);
    }

    @DeleteMapping("/user/{username}/{commentId}")
    public void deleteRecomment(@PathVariable String username, @PathVariable Long commentId) {
        recommentService.deleteRecomment(username, commentId);
    }

}
