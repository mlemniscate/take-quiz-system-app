package ir.maktabsharif.repository;

import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRole(
            String firstName,
            String lastName,
            String gender,
            Role role
    );
    List<User> findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleAndIsActive(
            String firstName,
            String lastName,
            String gender,
            Role role,
            Boolean isActive
    );
    List<User> findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleNot(
            String firstName,
            String lastName,
            String gender,
            Role role
    );

}
