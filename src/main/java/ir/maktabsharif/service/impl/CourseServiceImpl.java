package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

}
