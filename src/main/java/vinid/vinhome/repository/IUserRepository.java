package vinid.vinhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vinid.vinhome.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE user_phone = :user_phone")
    Optional<User> findUserByPhone(@Param("user_phone") String userPhone);

    @Query(nativeQuery = true, value = "SELECT * FROM user WHERE user_full_name like :keysearch OR user_phone like :keysearchtwo")
    List<User> findUserByName(@Param("keysearch") String keyOne,@Param("keysearchtwo") String keyTwo);

    @Query(nativeQuery = true , value = "SELECT * FROM user")
    List<User> getAll();
    @Query(nativeQuery = true , value = "SELECT * FROM user WHERE user_email = :email")
     User findByEmail(@Param("email")String email);
}
