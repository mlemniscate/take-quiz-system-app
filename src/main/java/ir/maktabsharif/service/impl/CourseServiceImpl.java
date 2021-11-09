package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.repository.AdminRepository;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
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

    public CourseServiceImpl(CourseRepository repository, AdminRepository adminRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        super(repository);
        this.adminRepository = adminRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

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

    @Override
    public Course saveStudentsToCourse(Student student, Long courseId) {
        Optional<Course> course = repository.findById(courseId);
        if(course.isPresent()) {
            course.get().getStudents().add(student);
            return repository.save(course.get());
        }
        else throw new RuntimeException("Course Id is wrong!");
    }

    @Override
    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        Optional<Course> courseOptional = repository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(courseOptional.isPresent() && studentOptional.isPresent()) {
            courseOptional.get().getStudents().remove(studentOptional.get());
            repository.save(courseOptional.get());
        }
    }

    @Override
    public List<Course> findTeacherCourses(Long teacherId) {
        return repository.findCourseByTeacherId(teacherId);
    }
}
