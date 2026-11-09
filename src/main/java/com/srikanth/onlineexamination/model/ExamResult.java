package com.srikanth.onlineexamination.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="exam_results")
public class ExamResult {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Student student;
 @ManyToOne(optional=false) private Exam exam;
 private int marks,totalQuestions; private double percentage; private LocalDateTime submittedAt;
 public ExamResult(){}
 public ExamResult(Student s,Exam e,int m,int total,double p,LocalDateTime at){student=s;exam=e;marks=m;totalQuestions=total;percentage=p;submittedAt=at;}
 public Long getId(){return id;} public Student getStudent(){return student;} public Exam getExam(){return exam;}
 public int getMarks(){return marks;} public int getTotalQuestions(){return totalQuestions;}
 public double getPercentage(){return percentage;} public LocalDateTime getSubmittedAt(){return submittedAt;}
}