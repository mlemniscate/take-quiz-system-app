package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.AdminRepository;
import ir.maktabsharif.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

}
