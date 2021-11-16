package ir.maktabsharif.service.impl;

import ir.maktabsharif.domain.MultiChoiceQuestion;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.MultiChoiceQuestionService;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceQuestionServiceImpl extends BaseQuestionServiceImpl<MultiChoiceQuestion, MultiChoiceQuestionRepository> implements MultiChoiceQuestionService {


    public MultiChoiceQuestionServiceImpl(MultiChoiceQuestionRepository repository, QuizRepository quizRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, QuizQuestionRepository quizQuestionRepository) {
        super(repository, quizRepository, teacherRepository, courseRepository, quizQuestionRepository);
    }
}
