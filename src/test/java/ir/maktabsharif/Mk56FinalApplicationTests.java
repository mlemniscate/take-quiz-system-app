package ir.maktabsharif;

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

	@Test
	void contextLoads() {
		
	}

}
