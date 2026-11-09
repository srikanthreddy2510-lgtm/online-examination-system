package com.srikanth.onlineexamination.controller;
import com.srikanth.onlineexamination.model.*; import com.srikanth.onlineexamination.repository.*;
import org.springframework.http.HttpStatus; import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.time.LocalDateTime; import java.util.*;
@RestController @RequestMapping("/api")
public class ResultController {
 private final StudentRepository students; private final ExamRepository exams; private final QuestionRepository questions; private final ExamResultRepository results;
 public ResultController(StudentRepository s,ExamRepository e,QuestionRepository q,ExamResultRepository r){students=s;exams=e;questions=q;results=r;}
 public record SubmissionRequest(Long studentId,Long examId,Map<Long,Integer> answers){}
 @PostMapping("/submissions") @Transactional public Map<String,Object> submit(@RequestBody SubmissionRequest r){
  Student s=students.findById(r.studentId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
  Exam e=exams.findById(r.examId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Exam not found"));
  List<Question> qs=questions.findByExamId(e.getId()); if(qs.isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This exam has no questions");
  int marks=0;for(Question q:qs){Integer a=r.answers()==null?null:r.answers().get(q.getId());if(a!=null&&a==q.getCorrectOption())marks++;}
  double pct=marks*100.0/qs.size();ExamResult saved=results.save(new ExamResult(s,e,marks,qs.size(),pct,LocalDateTime.now()));
  return Map.of("resultId",saved.getId(),"studentName",s.getName(),"examTitle",e.getTitle(),"marks",marks,"totalQuestions",qs.size(),"percentage",Math.round(pct*100.0)/100.0,"status",pct>=40?"PASS":"FAIL","submittedAt",saved.getSubmittedAt().toString());}
 @GetMapping("/results") @Transactional(readOnly=true) public List<Map<String,Object>> all(@RequestParam(required=false) Long studentId){
  List<ExamResult> list=studentId==null?results.findAll():results.findByStudentIdOrderBySubmittedAtDesc(studentId);
  return list.stream().map(this::map).toList();}
 private Map<String,Object> map(ExamResult r){return Map.of("id",r.getId(),"studentId",r.getStudent().getId(),"studentName",r.getStudent().getName(),"examId",r.getExam().getId(),"examTitle",r.getExam().getTitle(),"marks",r.getMarks(),"totalQuestions",r.getTotalQuestions(),"percentage",Math.round(r.getPercentage()*100.0)/100.0,"status",r.getPercentage()>=40?"PASS":"FAIL","submittedAt",r.getSubmittedAt().toString());}
}