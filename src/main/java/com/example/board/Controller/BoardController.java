package com.example.board.Controller;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;
import com.example.board.Service.BoardService;
import com.example.board.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.tokens.CommentToken;

import javax.swing.text.html.Option;
import java.util.*;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;


    @Autowired
    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("boardList")
    public String goBoard(Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards",boards);
        return "BoardList";
    }

    @GetMapping("board/{bnum}")
    public String page(@PathVariable("bnum") Long bnum, Model model){
        boardService.countView(bnum);
        Optional<Board> result = boardService.findByBnum(bnum);
        Board board = result.get();
//        Map<Long, Comment> comments = board.getComments();
        model.addAttribute("board",board);
//        model.addAttribute("comments", comments);
        return "page";
    }

    @GetMapping("board/{bnum}/edit")
    public String edit(@PathVariable("bnum") Long bnum, Model model){
        Optional<Board> result = boardService.findByBnum(bnum);
        Board board = result.get();

        model.addAttribute(board);

        return "editPost";
    }

    @PostMapping("board/{bnum}/edit")
    public String edit1(@PathVariable("bnum") Long bnum, BoardForm boardForm){
        Optional<Board> result = boardService.findByBnum(bnum);

        Board board = result.get();
        board.setBsubject(boardForm.getBsubject());
        board.setBcontent(boardForm.getBcontent());
        board.setBname(boardForm.getBname());

        boardService.edit(board);

        return "redirect:/board/{bnum}";
    }

    @GetMapping("board/{bnum}/delete")
    public String delete(@PathVariable("bnum") Long bnum) {

        boardService.deleteOne(bnum);
        return "redirect:/boardList";
    }

    @GetMapping("board/{bnum}/like")
    public String like(@PathVariable("bnum") Long bnum) {

        boardService.like(bnum);
        return "redirect:/board/{bnum}";
    }

    @GetMapping("makePost")
    public String write(){
        return "makePost";
    }

    @PostMapping("makePost")
    public String writePost(BoardForm boardForm){
        Board board = new Board();

        board.setBsubject(boardForm.getBsubject());
        board.setBcontent(boardForm.getBcontent());
        board.setBregdate(boardForm.getBregdate());
        board.setBname(boardForm.getBname());
        board.setBlike(0L);

        boardService.join(board);
        return "redirect:/boardList";
    }

}
