package com.example.board.Repository;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryCommentRepository implements CommentRepository{
    private static Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment, Long sequence) {
        comment.setCnum(++sequence);
        store.put(comment.getCnum(),comment);
        return comment;
    }

    @Override
    public void deleteOne(Long cnum) {
        store.remove(cnum);
    }
}
