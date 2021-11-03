package ir.maktabsharif.repository;

import ir.maktabsharif.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String adminUsername);
}
