package com.example.board.Repository;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository{
    Comment save(Comment comment, Long sequence);
    void deleteOne(Long cnum);
}
