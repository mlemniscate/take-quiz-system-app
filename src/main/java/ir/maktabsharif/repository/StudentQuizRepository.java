package ir.maktabsharif.repository;

import ir.maktabsharif.domain.StudentQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentQuizRepository extends JpaRepository<StudentQuiz, Long> {

    List<StudentQuiz> findAllByStudent_Id(Long id);

}
