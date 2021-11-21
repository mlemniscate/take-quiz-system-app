package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.*;
import ir.maktabsharif.repository.*;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;
    private final StudentQuizRepository studentQuizRepository;

    public CourseServiceImpl(CourseRepository repository, AdminRepository adminRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, QuizRepository quizRepository, StudentQuizRepository studentQuizRepository) {
        super(repository);
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.quizRepository = quizRepository;
        this.studentQuizRepository = studentQuizRepository;
    }

    // find all courses and return
    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    // save a course
    @Override
    public Course saveCourse(SaveCourseDTO course) {
        return repository.save(
                Course.builder()
                .title(course.getTitle())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .build()
        );
    }

    // add teacher to a course
    @Override
    public Course saveTeacherToCourse(Teacher teacher, Long courseId) {
        Optional<Course> courseOptional = repository.findById(courseId);
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacher.getId());
        if(courseOptional.isPresent() && teacherOptional.isPresent()) {
            Course course = courseOptional.get();
            Teacher teacherSave = teacherOptional.get();
            teacherSave.getCourses().add(course);
            course.setTeacher(teacherSave);
            repository.save(course);
            return course;
        }
        else throw new RuntimeException("Course Id is wrong!");
    }

    // add a student to course
    @Override
    public Course saveStudentsToCourse(Student student, Long courseId) {
        Optional<Course> course = repository.findById(courseId);
        if(course.isPresent()) {
            course.get().getStudents().add(student);
            return repository.save(course.get());
        }
        else throw new RuntimeException("Course Id is wrong!");
    }

    // delete a student from a course
    @Override
    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        Optional<Course> courseOptional = repository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(courseOptional.isPresent() && studentOptional.isPresent()) {
            courseOptional.get().getStudents().remove(studentOptional.get());
            repository.save(courseOptional.get());
        }
    }

    // find teacher all courses
    @Override
    public List<Course> findTeacherCourses(Long teacherId) {
        return repository.findCourseByTeacherId(teacherId);
    }

    // add and update a quiz in a course
    @Override
    public Course addQuiz(Quiz quiz, Long courseId) throws NotFoundException {
        Optional<Course> courseOptional = repository.findById(courseId);
        if(quiz.getId() == null) {
            Quiz quizSaved = quizRepository.save(quiz);
            if (courseOptional.isPresent()) {
                courseOptional.get().getStudents().forEach(student ->
                        studentQuizRepository.save(new StudentQuiz(true, null, quiz, student))
                );
                courseOptional.get().addQuiz(quizSaved);
                return repository.save(courseOptional.get());
            } else throw new NotFoundException("There is no course like this!");
        } else {
            Optional<Quiz> quizOptional = quizRepository.findById(quiz.getId());
            if(quizOptional.isPresent() && courseOptional.isPresent()) {
                quizOptional.get().setTitle(quiz.getTitle());
                quizOptional.get().setDescription(quiz.getDescription());
                quizOptional.get().setTime(quiz.getTime());
                quizOptional.get().setCourse(courseOptional.get());
                quizRepository.save(quizOptional.get());
                return repository.findById(courseId).get();
            } else throw new NotFoundException("There is no course and quiz like this!");
        }
    }

    @Override
    public List<Course> findStudentCourses(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return student.getCourses();
        } else return null;
    }
}
