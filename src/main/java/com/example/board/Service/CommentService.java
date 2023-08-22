package com.example.board.Service;

import com.example.board.Domain.Board;
import com.example.board.Domain.Comment;
import com.example.board.Repository.BoardRepository;
import com.example.board.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long join(Comment comment, Long sequence){
        commentRepository.save(comment, sequence);
        return comment.getBnum();
    }

    public void delete(Long cnum){
        commentRepository.deleteOne(cnum);
    }
}
