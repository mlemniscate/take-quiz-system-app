package ir.maktabsharif;

import com.github.javafaker.Faker;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Mk56FinalApplicationTests {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    void contextLoads() {
//		Boolean isActiveUser = null;
//		List<Boolean> isActive = Objects.isNull(isActiveUser) ? List.of(true, false) :
//				isActiveUser ? List.of(true) : List.of(false);
//		List<User> m = userRepository.findAll("", "", "", "", isActive);
//		m.forEach(System.out::println);
    }

    @Test
    void insertData() {
//        Faker faker = new Faker();
//        IntStream.range(1, 21).forEach(index -> {
//            int random = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
//            Gender gender = random % 2 == 0 ? Gender.MALE : Gender.FEMALE;
//            Boolean isActive = random % 2 == 0;
//            Student student = getStudent(faker, gender, isActive);
//            studentRepository.save(student);
//        });
//        IntStream.range(1, 21).forEach(index -> {
//            int random = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
//            Gender gender = random % 2 == 0 ? Gender.MALE : Gender.FEMALE;
//            Boolean isActive = random % 2 == 0;
//            Teacher teacher = getTeacher(faker, gender, isActive);
//            teacherRepository.save(teacher);
//        });
//        IntStream.range(1, 11).forEach(index -> {
//            Course course = getCourse(faker);
//            courseRepository.save(course);
//        });
    }

    private Course getCourse(Faker faker) {
        return Course.builder()
                .title(faker.name().title())
                .startDate("1400/08/15")
                .endDate("1400/12/20")
                .build();
    }

    private Teacher getTeacher(Faker faker, Gender gender, Boolean isActive) {
        return Teacher.builder()
                .username(faker.name().username())
                .password(faker.bothify("????????######"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .gender(gender)
                .role(Role.TEACHER)
                .isActive(isActive)
                .build();
    }

    private Student getStudent(Faker faker, Gender gender, Boolean isActive) {
        return Student.builder()
                .username(faker.name().username())
                .password(faker.bothify("????????######"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .gender(gender)
                .role(Role.STUDENT)
                .isActive(isActive)
                .build();
    }

}
