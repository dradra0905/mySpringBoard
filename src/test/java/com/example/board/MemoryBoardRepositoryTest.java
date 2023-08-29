package com.example.board;

import com.example.board.Domain.Board;
import com.example.board.Repository.MemoryBoardRepository;
import org.junit.jupiter.api.Test;

public class MemoryBoardRepositoryTest {
    MemoryBoardRepository repository = new MemoryBoardRepository();

    @Test
    public void save(){
        Board board = new Board();
        board.setBsubject("hehe");
        board.setBcontent("ehehe");

        repository.save(board);
    }
}
