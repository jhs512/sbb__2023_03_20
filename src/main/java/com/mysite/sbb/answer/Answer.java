package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// answer 테이블이 생김
@Getter
@Setter
@Entity
@ToString
public class Answer {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id; // INT id

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private LocalDateTime createDate; // DATETIME

    @ManyToOne
    @ToString.Exclude // ToString 대상에서 제외
    private Question question;

    @ManyToOne
    private SiteUser author;
}
