package com.example.board.Repository;

import com.example.board.Domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Optional<Board> findByBnum(Long bnum);
    void deleteOne(Long bnum);
    void countView(Long bnum);
    Board edit(Board board);
}
