package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.CommentRequestDto;
import com.finalpjc.ott.model.Comment;
import com.finalpjc.ott.repository.CommentLikeItRepository;
import com.finalpjc.ott.repository.CommentRepository;
import com.finalpjc.ott.repository.RecommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecommentRepository recommentRepository;
    private final CommentLikeItRepository commentLikeItRepository;

    @Transactional
    public void createComment(CommentRequestDto requestDto) { // 댓글 작성
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long id, CommentRequestDto requestDto) { // 댓글 수정
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("ID not found")
        );
        comment.commentUpdate(requestDto);
    }

    public List<Comment> getCommentWithLikeItCounter(List<Comment> commentList, String username) { // 댓글 읽기
        for (Comment value : commentList) {
            Long commentId = value.getId();
            Long recommentCount = recommentRepository.countByCommentId(commentId);
            Long likesCount = commentLikeItRepository.countByCommentId(commentId);

            value.addRecommentCount(recommentCount);
            value.addCommentLikeItCount(likesCount);

            value.changeLikeItChecker(commentLikeItRepository.findByUsernameAndCommentId(username, commentId).isPresent());
        }
        return commentList;
    }

    public void deleteComment(String username, Long articleId) {
        commentRepository.deleteByUsernameAndArticleId(username, articleId);
    }

}
