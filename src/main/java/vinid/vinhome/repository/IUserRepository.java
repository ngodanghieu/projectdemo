package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE user_phone = :user_phone")
    Optional<User> findUserByPhone(@Param("user_phone") String userPhone);
}
