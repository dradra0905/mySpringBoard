package com.example.board.Controller;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;
import com.example.board.Service.BoardService;
import com.example.board.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

    @Autowired
    public CommentController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @PostMapping("board/{bnum}/comment")
    public String writeComment(@PathVariable("bnum") Long bnum, CommentForm commentForm){
        Optional<Board> result = boardService.findByBnum(bnum);
        Board board = result.get();

        Comment comment = new Comment();
        comment.setBnum(bnum);
        comment.setComment(commentForm.getComment());
        comment.setName(commentForm.getName());
        comment.setRegdate(commentForm.getRegdate());

        board.save(comment);

        return "redirect:/board/{bnum}";
    }
    @GetMapping("board/{bnum}/comment/delete/{cnum}")
    public String deleteComment(@PathVariable("bnum") Long bnum, @PathVariable("cnum") Long cnum){
        Optional<Board> result = boardService.findByBnum(bnum);
        Board board = result.get();

        Map<Long, Comment> comments = board.getComments();
        comments.remove(cnum);

        return "redirect:/board/{bnum}";
    }
}
