package com.srikanth.onlineexamination.repository;
import com.srikanth.onlineexamination.model.Student; import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student,Long>{boolean existsByEmail(String email);}