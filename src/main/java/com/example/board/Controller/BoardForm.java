package com.example.board.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardForm {
    private String bsubject;
    private String bcontent;
    private String bname;
    private String bregdate;
    private LocalDateTime localDateTime = LocalDateTime.now();
    private Long blike;

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

    public String getBregdate() {
        bregdate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
