package com.srikanth.onlineexamination.config;
import com.srikanth.onlineexamination.model.*; import com.srikanth.onlineexamination.repository.*;
import org.springframework.boot.CommandLineRunner; import org.springframework.stereotype.Component;
@Component
public class DataInitializer implements CommandLineRunner {
 private final StudentRepository students; private final ExamRepository exams; private final QuestionRepository questions;
 public DataInitializer(StudentRepository s,ExamRepository e,QuestionRepository q){students=s;exams=e;questions=q;}
 @Override public void run(String... args){
  if(students.count()==0){students.save(new Student("Srikanth Reddy","srikanth@example.com"));students.save(new Student("Ananya Sharma","ananya@example.com"));students.save(new Student("Rahul Kumar","rahul@example.com"));}
  if(exams.count()==0){Exam e=exams.save(new Exam("Java Fundamentals","Test your knowledge of core Java concepts.",20,true));
   questions.save(new Question(e,"Which keyword is used to inherit a class in Java?","implements","extends","inherits","super",1));
   questions.save(new Question(e,"Which method is the entry point of a Java application?","start()","run()","main()","init()",2));
   questions.save(new Question(e,"Which collection does not allow duplicate elements?","ArrayList","LinkedList","HashSet","Vector",2));
   questions.save(new Question(e,"Which keyword prevents a variable from being reassigned?","static","final","const","private",1));
   questions.save(new Question(e,"Which company originally developed Java?","Microsoft","Sun Microsystems","IBM","Oracle",1));}
 }
}