package com.srikanth.onlineexamination.controller;
import com.srikanth.onlineexamination.model.*; import com.srikanth.onlineexamination.repository.*;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.*;
@RestController @RequestMapping("/api/exams")
public class ExamController {
 private final ExamRepository exams; private final QuestionRepository questions;
 public ExamController(ExamRepository e,QuestionRepository q){exams=e;questions=q;}
 public record ExamRequest(@NotBlank String title,String description,@Min(1) @Max(300) int durationMinutes,boolean active){}
 public record QuestionRequest(@NotBlank String text,@NotBlank String optionA,@NotBlank String optionB,@NotBlank String optionC,@NotBlank String optionD,@Min(0) @Max(3) int correctOption){}
 @GetMapping public List<Exam> all(){return exams.findAll();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Exam create(@Valid @RequestBody ExamRequest r){return exams.save(new Exam(r.title(),r.description(),r.durationMinutes(),r.active()));}
 @GetMapping("/{id}/questions") public List<Map<String,Object>> getQuestions(@PathVariable Long id){
  if(!exams.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found");
  return questions.findByExamId(id).stream().map(q->Map.<String,Object>of("id",q.getId(),"text",q.getText(),"options",List.of(q.getOptionA(),q.getOptionB(),q.getOptionC(),q.getOptionD()))).toList();}
 @PostMapping("/{id}/questions") @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> addQuestion(@PathVariable Long id,@Valid @RequestBody QuestionRequest r){
  Exam e=exams.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
  Question q=questions.save(new Question(e,r.text(),r.optionA(),r.optionB(),r.optionC(),r.optionD(),r.correctOption()));
  return Map.of("id",q.getId(),"message","Question added successfully");}
 @PutMapping("/{id}") public Exam update(@PathVariable Long id,@Valid @RequestBody ExamRequest r){
  Exam e=exams.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
  e.setTitle(r.title());e.setDescription(r.description());e.setDurationMinutes(r.durationMinutes());e.setActive(r.active());return exams.save(e);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){
  Exam e=exams.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
  questions.deleteAll(questions.findByExamId(id));exams.delete(e);}
}