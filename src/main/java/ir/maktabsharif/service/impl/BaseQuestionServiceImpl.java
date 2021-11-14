package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.*;
import ir.maktabsharif.repository.BaseQuestionRepository;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.service.BaseQuestionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseQuestionServiceImpl<E extends BaseQuestion, R extends BaseQuestionRepository<E>>
        extends BaseServiceImpl<E, Long, R> implements BaseQuestionService<E> {

    private final QuizRepository quizRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public BaseQuestionServiceImpl(R repository, QuizRepository quizRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        super(repository);
        this.quizRepository = quizRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<E> findByCourseIdAndTeacherId(Long courseId, Long teacherId) {
        return repository.findByCourseIdAndTeacherId(courseId, teacherId);
    }

    @Override
    @Transactional
    public E saveNotSecure(E question, Long courseId, Long teacherId, Long quizId, Integer score) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        E questionSave = repository.save(question);
        if (courseOptional.isPresent() && quizOptional.isPresent() && teacherOptional.isPresent()) {
            questionSave.setCourse(courseOptional.get());
            questionSave.setTeacher(teacherOptional.get());
            questionSave.addQuizQuestion(
                    QuizQuestion.builder()
                            .quiz(quizOptional.get())
                            .question(questionSave)
                            .score(score)
                            .build()
            );
            return repository.save(questionSave);
        } else return null;
    }

    @Override
    @Transactional
    public List<E> findAllByQuizId(Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            List<QuizQuestion> quizQuestions = quizOptional.get().getQuizQuestions();
            List<E> questions = new ArrayList<>();
            quizQuestions.forEach(quizQuestion -> {
                E question = repository.findById(quizQuestion.getQuestion().getId()).get();
                question.setScore(quizQuestion.getScore());
                questions.add(question);
            });
            return questions;
        } else return null;
    }
}
