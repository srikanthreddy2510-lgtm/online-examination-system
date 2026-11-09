package com.srikanth.onlineexamination.repository;
import com.srikanth.onlineexamination.model.ExamResult; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ExamResultRepository extends JpaRepository<ExamResult,Long>{List<ExamResult> findByStudentIdOrderBySubmittedAtDesc(Long studentId);}