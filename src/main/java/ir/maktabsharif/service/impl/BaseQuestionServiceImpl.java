package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.BaseQuestionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BaseQuestionServiceImpl<E extends BaseQuestion, R extends BaseQuestionRepository<E>>
        extends BaseServiceImpl<E, Long, R> implements BaseQuestionService<E> {

    private final QuizRepository quizRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final QuizQuestionRepository quizQuestionRepository;

    public BaseQuestionServiceImpl(R repository, QuizRepository quizRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, QuizQuestionRepository quizQuestionRepository) {
        super(repository);
        this.quizRepository = quizRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.quizQuestionRepository = quizQuestionRepository;
    }

    @Override
    public List<E> findByCourseIdAndTeacherId(Long courseId, Long teacherId) {
        return repository.findByCourseIdAndTeacherId(courseId, teacherId);
    }

    //TODO write comment
    @Override
    @Transactional
    public E saveNotSecure(E question, Long courseId, Long teacherId, Long quizId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        E questionSave = repository.save(question);
        if (courseOptional.isPresent() && quizOptional.isPresent() && teacherOptional.isPresent()) {
            questionSave.setCourse(courseOptional.get());
            questionSave.setTeacher(teacherOptional.get());
            QuizQuestion quizQuestion = getExistQuizQuestion(questionSave, quizOptional.get().getQuizQuestions());
            if (Objects.isNull(quizQuestion)) {
                questionSave.addQuizQuestion(
                        QuizQuestion.builder()
                                .quiz(quizOptional.get())
                                .question(questionSave)
                                .score(question.getScore())
                                .build()
                );
            } else {
                int indexOfQuizQuestion = quizOptional.get().getQuizQuestions().indexOf(quizQuestion);
                quizQuestion.setScore(question.getScore());
                quizOptional.get().getQuizQuestions().set(
                        indexOfQuizQuestion,
                        quizQuestion
                );
                quizRepository.save(quizOptional.get());
            }
            return repository.save(questionSave);
        } else return null;
    }

    private QuizQuestion getExistQuizQuestion(E question, List<QuizQuestion> quizQuestionList) {
        for (QuizQuestion quizQuestion : quizQuestionList) {
            if (question.getId().equals(quizQuestion.getQuestion().getId())) return quizQuestion;
        }
        return null;
    }

    @Override
    @Transactional
    public List<E> findAllByQuizId(Long quizId) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            List<QuizQuestion> quizQuestions = quizOptional.get().getQuizQuestions();
            List<E> questions = new ArrayList<>();
            quizQuestions.forEach(quizQuestion -> {
                Optional<E> question = repository.findById(quizQuestion.getQuestion().getId());
                if (question.isPresent()) {
                    question.get().setScore(quizQuestion.getScore());
                    questions.add(question.get());
                }
            });
            return questions;
        } else return null;
    }

    // TODO write comment
    @Override
    public void deleteNotSecure(Long quizId, E question) {
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if (quizOptional.isPresent()) {
            QuizQuestion quizQuestion = deleteQuestionFromQuizQuestionList(quizOptional.get(), question);
            if(!Objects.isNull(quizQuestion)) {
                quizQuestionRepository.delete(quizQuestion);
                quizRepository.save(quizOptional.get());
            }
        }
    }

    private QuizQuestion deleteQuestionFromQuizQuestionList(Quiz quiz, E question) {
        for (QuizQuestion quizQuestion : quiz.getQuizQuestions()) {
            if (quizQuestion.getQuestion().getId().equals(question.getId())) {
                quiz.getQuizQuestions().remove(quizQuestion);
                return quizQuestion;
            }
        }
        return null;
    }

}
