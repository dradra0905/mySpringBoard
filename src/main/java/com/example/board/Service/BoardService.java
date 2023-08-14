package com.example.board.Service;

import com.example.board.Domain.Board;
import com.example.board.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long join(Board board){
        boardRepository.save(board);
        return board.getBnum();
    }

    public void countView(Long bnum){
        boardRepository.countView(bnum);
    }

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    public Optional<Board> findByBnum(Long bnum){
        return boardRepository.findByBnum(bnum);
    }

    public void deleteOne(Long bnum){
        boardRepository.deleteOne(bnum);
    }

    public Long edit(Board board){
        boardRepository.edit(board);
        return board.getBnum();
    }

}
