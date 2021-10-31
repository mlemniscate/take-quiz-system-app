package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

}
