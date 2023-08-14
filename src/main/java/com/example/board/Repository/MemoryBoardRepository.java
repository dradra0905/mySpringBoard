package com.example.board.Repository;

import com.example.board.Domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository{
    private static Map<Long, Board> store = new HashMap<>();
    private static long sequence = 0;

    @Override
    public Board save(Board board) {
        board.setBnum(++sequence);
        store.put(board.getBnum(),board);
        return board;
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Board> findByBnum(Long bnum) {
        return Optional.ofNullable(store.get(bnum));
    }

    @Override
    public void deleteOne(Long bnum) {
        store.remove(bnum);
    }

    @Override
    public void countView(Long bnum) {
        Board board = store.get(bnum);
        int vc = board.getViewCnt();
        board.setViewCnt(vc + 1);
        store.put(board.getBnum(),board);
    }

    @Override
    public Board edit(Board board) {
        store.put(board.getBnum(),board);
        return board;
    }
}
