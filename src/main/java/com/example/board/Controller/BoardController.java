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
        Optional<Board> result = boardService.findByBnum(bnum);
        Board board = result.get();
        Map<Long, Comment> comments = board.getComments();
        boardService.countView(bnum);
        model.addAttribute("board",board);
        model.addAttribute("comments", comments);
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
        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());
        board.setName(boardForm.getName());

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

        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());
        board.setRegdate(boardForm.getRegdate());
        board.setName(boardForm.getName());
        board.setLike(0L);

        boardService.join(board);
        return "redirect:/boardList";
    }

}
