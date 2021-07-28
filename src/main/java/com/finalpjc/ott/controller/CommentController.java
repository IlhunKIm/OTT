package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.CommentRequestDto;
import com.finalpjc.ott.model.Comment;
import com.finalpjc.ott.repository.CommentRepository;
import com.finalpjc.ott.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PostMapping("/user/comment/") // 댓글 달기
    public void createComment(@RequestBody CommentRequestDto requestDto) {
        commentService.createComment(requestDto);
    }

    @GetMapping("/user/comment/{username}/{articleId}") // 댓글 가져오기(username, articleId)
    public List<Comment> readComment(@PathVariable String username, @PathVariable Long articleId) {
        List<Comment> commentList = commentRepository.findAllByUsernameAndArticleId(username, articleId);
        return commentService.getCommentWithLikeItCounter(commentList, username);

    }

    @PutMapping("/user/comment/{commentId}") //댓글 수정
    public void updateComment(@PathVariable Long commentId, CommentRequestDto requestDto ) {
        commentService.updateComment(commentId, requestDto);
    }

    @DeleteMapping("user/comment/{username}/{commentId}") // 댓글 삭제
    public void deleteComment(@PathVariable String username, @PathVariable Long commentId) {
        commentService.deleteComment(username, commentId);
    }


}
