package ir.maktabsharif.repository;

import ir.maktabsharif.domain.MultiChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiChoiceQuestionRepository extends JpaRepository<MultiChoiceQuestion, Long> {
}
