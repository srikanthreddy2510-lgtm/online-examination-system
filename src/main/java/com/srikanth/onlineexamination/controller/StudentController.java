package com.srikanth.onlineexamination.controller;
import com.srikanth.onlineexamination.model.Student; import com.srikanth.onlineexamination.repository.StudentRepository;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.List;
@RestController @RequestMapping("/api/students")
public class StudentController {
 private final StudentRepository repo; public StudentController(StudentRepository repo){this.repo=repo;}
 public record StudentRequest(@NotBlank String name,@NotBlank @Email String email){}
 @GetMapping public List<Student> all(){return repo.findAll();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Student create(@Valid @RequestBody StudentRequest r){
  if(repo.existsByEmail(r.email())) throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
  return repo.save(new Student(r.name(),r.email()));}
 @PutMapping("/{id}") public Student update(@PathVariable Long id,@Valid @RequestBody StudentRequest r){
  Student s=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
  s.setName(r.name());s.setEmail(r.email());return repo.save(s);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){
  if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found");repo.deleteById(id);}
}