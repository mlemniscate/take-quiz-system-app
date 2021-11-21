package ir.maktabsharif.repository;

import ir.maktabsharif.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Modifying
    @Query(value = "UPDATE users u SET u.user_type=(?1) WHERE u.username=(?2);", nativeQuery = true)
    void editUserTypeNative(String userType, String username);

    @Modifying
    @Query(value = "SELECT * FROM users u WHERE u.first_name LIKE %?1% AND u.last_name LIKE %?2% AND u.gender LIKE ?3% AND u.user_type LIKE %?4% AND u.user_type != 'Admin' AND is_active IN ?5", nativeQuery = true)
    List<User> findAll(String firstName, String lastName, String gender, String userType, Collection<Boolean> isActive);


}
