package ir.maktabsharif.service.impl;

import ir.maktabsharif.domain.DescriptiveQuestion;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.DescriptiveQuestionRepository;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.service.DescriptiveQuestionService;
import org.springframework.stereotype.Service;

@Service
public class DescriptiveQuestionServiceImpl extends BaseQuestionServiceImpl<DescriptiveQuestion, DescriptiveQuestionRepository> implements DescriptiveQuestionService {


    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository, QuizRepository quizRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        super(repository, quizRepository, teacherRepository, courseRepository);
    }
}
