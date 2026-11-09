package com.srikanth.onlineexamination.model;
import jakarta.persistence.*;
@Entity @Table(name="exams")
public class Exam {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(length=1000) private String description;
 private int durationMinutes=30; private boolean active=true;
 public Exam(){}
 public Exam(String title,String description,int durationMinutes,boolean active){this.title=title;this.description=description;this.durationMinutes=durationMinutes;this.active=active;}
 public Long getId(){return id;} public String getTitle(){return title;} public String getDescription(){return description;}
 public int getDurationMinutes(){return durationMinutes;} public boolean isActive(){return active;}
 public void setTitle(String v){title=v;} public void setDescription(String v){description=v;}
 public void setDurationMinutes(int v){durationMinutes=v;} public void setActive(boolean v){active=v;}
}