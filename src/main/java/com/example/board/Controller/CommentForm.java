package com.example.board.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentForm {
    private String name;
    private String comment;
    private String regdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        regdate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.regdate = regdate;
    }

    private LocalDateTime localDateTime = LocalDateTime.now();

}
