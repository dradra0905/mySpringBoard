package com.example.board.Controller;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;
import com.example.board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
    private final BoardService boardService;

    @Autowired
    public CommentController(BoardService boardService) {
        this.boardService = boardService;
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

        board.getComments().add(comment);

        return "redirect:/board/{bnum}";
    }

}
