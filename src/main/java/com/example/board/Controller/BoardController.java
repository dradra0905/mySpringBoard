package com.example.board.Controller;

import com.example.board.Domain.Board;
import com.example.board.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
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
        boardService.countView(bnum);
        model.addAttribute("board",board);
        return "page";
    }

    @GetMapping("delete/{bnum}")
    public String delete(@PathVariable("bnum") Long bnum){
        boardService.deleteOne(bnum);
        return "redirect:/boardList";
    }

    @GetMapping("makePost")
    public String write(){
        return "makePost";
    }

    @PostMapping("makePost")
    public String write1(BoardForm boardForm){
        Board board = new Board();

        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());
        board.setRegdate(boardForm.getRegdate());
        board.setName(boardForm.getName());

        boardService.join(board);
        return "redirect:/boardList";
    }
}
