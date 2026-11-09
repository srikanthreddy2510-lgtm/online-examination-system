package com.srikanth.onlineexamination.model;
import jakarta.persistence.*;
@Entity @Table(name="questions")
public class Question {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="exam_id") private Exam exam;
 @Column(nullable=false,length=1500) private String text;
 @Column(nullable=false) private String optionA,optionB,optionC,optionD;
 private int correctOption;
 public Question(){}
 public Question(Exam e,String t,String a,String b,String c,String d,int correct){exam=e;text=t;optionA=a;optionB=b;optionC=c;optionD=d;correctOption=correct;}
 public Long getId(){return id;} public Exam getExam(){return exam;} public String getText(){return text;}
 public String getOptionA(){return optionA;} public String getOptionB(){return optionB;} public String getOptionC(){return optionC;} public String getOptionD(){return optionD;}
 public int getCorrectOption(){return correctOption;}
}