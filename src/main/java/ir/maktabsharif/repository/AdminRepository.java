package ir.maktabsharif.repository;

import ir.maktabsharif.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String adminUsername);
}
