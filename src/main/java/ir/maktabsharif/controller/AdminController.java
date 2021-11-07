package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.AdminMapper;
import ir.maktabsharif.domain.Admin;
import ir.maktabsharif.service.AdminService;
import ir.maktabsharif.service.dto.AdminDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseRestFul<Admin, AdminDTO, Long, AdminService, AdminMapper> {

    public AdminController(AdminService service, AdminMapper mapper) {
        super(service, mapper);
    }
}
