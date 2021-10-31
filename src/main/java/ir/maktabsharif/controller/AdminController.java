package ir.maktabsharif.controller;

import ir.maktabsharif.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

}
