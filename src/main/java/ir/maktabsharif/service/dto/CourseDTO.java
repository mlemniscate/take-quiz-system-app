package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO extends BaseDTO<Long> {

    private String title;
    private String startDate;
    private String endDate;
    private TeacherDTO teacher;
    private List<StudentDTO> students;
    private List<QuizDTO> quizzes;

}
