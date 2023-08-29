package com.example.board.Repository;

import com.example.board.Domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcTemplateBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert
                .withTableName("board")
                .usingColumns("bsubject", "bname", "bcontent", "blike", "bregdate", "bviewcnt")
                .usingGeneratedKeyColumns("bnum");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("bsubject", board.getBsubject());
        parameters.put("bname", board.getBname());
        parameters.put("bcontent", board.getBcontent());
        parameters.put("blike", board.getBlike());
        parameters.put("bregdate", board.getBregdate());
        parameters.put("bviewcnt", board.getBviewcnt());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setBnum(key.longValue());
        /*String sql = "insert into board (bsubject, name, content, like, regdate, viewcnt) values (?, ?, ?, ?, ?, ?)";
        Object[] params = {board.getSubject(), board.getName(), board.getContent(), board.getLike(), board.getRegdate(), board.getViewCnt()};

        jdbcTemplate.update(sql, params);*/
        return board;
    }

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }

    @Override
    public Optional<Board> findByBnum(Long bnum) {
        List<Board> result = jdbcTemplate.query("select * from board where bnum = ?", boardRowMapper(), bnum);
        return result.stream().findAny();
    }

    @Override
    public void deleteOne(Long bnum) {
        jdbcTemplate.update("delete from board where bnum = ?", bnum);
    }

    @Override
    public void countView(Long bnum) {
        Optional<Board> result = findByBnum(bnum);
        Board board = result.get();

        int v = board.getBviewcnt();
        v+=1;
        board.setBviewcnt(v);
        jdbcTemplate.update("update board set bviewcnt = ? where bnum=?", board.getBviewcnt(), board.getBnum());
    }

    @Override
    public Board edit(Board board) {
        jdbcTemplate.update("update board set bsubject = ?, bcontent = ?, bname = ? where bnum = ?", board.getBsubject(), board.getBcontent(), board.getBname(), board.getBnum());
        return board;
    }

    @Override
    public void like(Long bnum) {
        Optional<Board> result = findByBnum(bnum);
        Board board = result.get();

        int v = board.getBviewcnt();
        v-=1;
        board.setBviewcnt(v);

        Long like = board.getBlike();
        like+=1;
        board.setBlike(like);
        jdbcTemplate.update("update board set blike = ?, bviewcnt = ? where bnum=?", board.getBlike(), board.getBviewcnt(), board.getBnum());
    }

    private RowMapper<Board> boardRowMapper(){
        return (rs, rowNum) ->{
            Board board = new Board();
            board.setBnum(rs.getLong("bnum"));
            board.setBname(rs.getString("bname"));
            board.setBlike(rs.getLong("blike"));
            board.setBsubject(rs.getString("bsubject"));
            board.setBviewcnt(rs.getInt("bviewcnt"));
            board.setBregdate(rs.getString("bregdate"));
            board.setBcontent(rs.getString("bcontent"));
            return board;
        };
    }
}
