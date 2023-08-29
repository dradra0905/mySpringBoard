package com.example.board.Domain;

import org.yaml.snakeyaml.tokens.CommentToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Long bnum;
    private String bsubject;
    private String bcontent;
    private String bname;
    private int bviewcnt;
    private String bregdate;
    private Long blike;

    /*private Map<Long, Comment> comments = new HashMap<>();
    private long sequence = 0;

    public Comment save(Comment comment) {
        comment.setCnum(++sequence);
        comments.put(comment.getCnum(),comment);
        return comment;
    }

    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }*/

    public Long getBnum() {
        return bnum;
    }

    public void setBnum(Long bnum) {
        this.bnum = bnum;
    }

    public String getBsubject() {
        return bsubject;
    }

    public void setBsubject(String bsubject) {
        this.bsubject = bsubject;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBviewcnt() {
        return bviewcnt;
    }

    public void setBviewcnt(int bviewcnt) {
        this.bviewcnt = bviewcnt;
    }

    public String getBregdate() {
        return bregdate;
    }

    public void setBregdate(String bregdate) {
        this.bregdate = bregdate;
    }

    public Long getBlike() {
        return blike;
    }

    public void setBlike(Long blike) {
        this.blike = blike;
    }
}
