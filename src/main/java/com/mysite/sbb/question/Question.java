package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// question 테이블이 생김
@Getter
@Setter
@Entity
@ToString
public class Question {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id; // INT id

    @Column(length = 200) // VARCHAR(200)
    private String subject;

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private LocalDateTime createDate; // DATETIME

    // OneToMany 자바세상에서의 편의를 위해서 필드 생성
    // 이 녀석은 실제 DB 테이블에 칼럼이 생성되지 않는다.
    // DB는 배열이나 리스트를 저장할 수 없다.
    // 칼럼에 저장할 수 없다.
    // 만들어도 되고 안 만들어도 됩니다.
    // 다만 만들면 해당 객체(질문객체)에서 관련된 답변들을 찾을 때 편합니다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.EXTRA) // answerList.size(); 함수가 실행될 때 SELECT COUNT 실행
    // OneToMany 에는 직접객체초기화
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(Answer a) {
        a.setQuestion(this);
        answerList.add(a);
    }
}
