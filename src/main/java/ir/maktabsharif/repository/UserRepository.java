package ir.maktabsharif.repository;

import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    List<User> findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleNotAndIsActive(
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

    List<User> findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleAndIsActive(String firstName, String lastName, String gender, Role role, Boolean isActive);

    @Modifying
    @Query(value = "UPDATE users u SET u.user_type=(?1) WHERE u.username=(?2);", nativeQuery = true)
    void editUserTypeNative(String userType, String username);


}
