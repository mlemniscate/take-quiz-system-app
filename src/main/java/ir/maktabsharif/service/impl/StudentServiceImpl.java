package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

}
