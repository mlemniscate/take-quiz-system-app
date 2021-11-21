package ir.maktabsharif.service.dto.extra;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.service.dto.QuizDTO;
import ir.maktabsharif.service.dto.TeacherDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseWithoutStudentsDTO extends BaseDTO<Long> {

    private String title;
    private String startDate;
    private String endDate;
    private TeacherDTO teacher;
    private List<QuizDTO> quizzes;

}
