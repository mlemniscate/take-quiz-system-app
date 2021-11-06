package ir.maktabsharif;

import com.github.javafaker.Faker;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@SpringBootTest
class Mk56FinalApplicationTests {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TeacherRepository teacherRepository;

	@Test
	void contextLoads() {
		Boolean isActiveUser = null;
		List<Boolean> isActive = Objects.isNull(isActiveUser) ? List.of(true, false) :
				isActiveUser ? List.of(true) : List.of(false);
		List<User> m = userRepository.findAll("", "", "", "", isActive);
		m.forEach(System.out::println);
	}

	@Test
	void insertData() {
		Faker faker = new Faker();
		IntStream.range(1, 21).forEach(index -> {
			Gender gender = index % 2 == 0 ? Gender.MALE : Gender.FEMALE;
			Boolean isActive = index % 2 == 0;
			Student student = new Student(
					faker.name().username(),
					faker.bothify("????????######"),
					faker.name().firstName(),
					faker.name().lastName(),
					faker.internet().emailAddress(),
					gender,
					Role.STUDENT,
					isActive
			);
			studentRepository.save(student);
		});
		IntStream.range(1, 21).forEach(index -> {
			Gender gender = index % 2 == 0 ? Gender.MALE : Gender.FEMALE;
			Boolean isActive = index % 2 == 0;
			Teacher teacher = new Teacher(
					faker.name().username(),
					faker.bothify("????????######"),
					faker.name().firstName(),
					faker.name().lastName(),
					faker.internet().emailAddress(),
					gender,
					Role.TEACHER,
					isActive
			);
			teacherRepository.save(teacher);
		});
	}

}
