package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.StudentQuiz;
import ir.maktabsharif.repository.StudentQuizRepository;
import ir.maktabsharif.service.StudentQuizService;
import org.springframework.stereotype.Service;

@Service
public class StudentQuizServiceImpl extends BaseServiceImpl<StudentQuiz, Long, StudentQuizRepository> implements StudentQuizService {

    public StudentQuizServiceImpl(StudentQuizRepository repository) {
        super(repository);
    }
}
