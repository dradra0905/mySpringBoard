package com.example.board.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardForm {
    private String subject;
    private String content;
    private String name;
    private String regdate;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegdate() {
        regdate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return regdate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
