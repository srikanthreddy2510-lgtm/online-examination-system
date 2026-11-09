package com.srikanth.onlineexamination.repository;
import com.srikanth.onlineexamination.model.Question; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface QuestionRepository extends JpaRepository<Question,Long>{List<Question> findByExamId(Long examId); long countByExamId(Long examId);}