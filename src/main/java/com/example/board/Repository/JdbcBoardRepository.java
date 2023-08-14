package com.example.board.Repository;

import com.example.board.Domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBoardRepository implements BoardRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Board> findByBnum(Long bnum) {
        return Optional.empty();
    }

    @Override
    public Board save(Board board) {
        return null;
    }

    @Override
    public List<Board> findAll() {
        return null;
    }

    @Override
    public void deleteOne(Long bnum) {

    }

    @Override
    public void countView(Long bnum) {

    }
}
