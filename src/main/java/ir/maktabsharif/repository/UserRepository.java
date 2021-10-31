package ir.maktabsharif.repository;

import ir.maktabsharif.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
