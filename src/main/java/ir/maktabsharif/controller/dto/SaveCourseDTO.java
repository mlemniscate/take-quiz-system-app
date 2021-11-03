package ir.maktabsharif.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaveCourseDTO {
    private String title;
    private String description;
    private Integer hours;
    private String startDate;
    private String endDate;
    private String adminUsername;
}
