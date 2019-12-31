package vinid.vinhome.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vinid.vinhome.entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void findAll() {
        User user1 = new User();
        user1.setUserEmail("sam.smith@gmail.com");
        user1.setUserPhone("1234567890");
        user1.setUserHash("1234567890");
        user1.setUserCreatedOn(new Date());
        user1.setUserCreatedBy("danghiue");

        User user2 = new User();
        user2.setUserPhone("1234567890");
        user2.setUserHash("1234567890");
        user2.setUserEmail("joe@gmail.com");
        user2.setUserCreatedOn(new Date());
        user2.setUserCreatedBy("danghiue");
        User user3 = new User();
        user3.setUserEmail("jame@gmail.com");
        user3.setUserPhone("1234567890");
        user3.setUserHash("1234567890");
        user3.setUserCreatedOn(new Date());
        user3.setUserCreatedBy("danghiue");

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.flush();

        List<User> allUsers = userRepository.findAll();

        assertThat(allUsers).hasSize(3).extracting(User::getUserEmail).containsOnly(user1.getUserEmail(), user2.getUserEmail(), user3.getUserEmail());
    }

    @Test
    public void findByEmail() throws ParseException {
        User user = new User();
        user.setUserEmail("sam.smith@gmail.com");
        user.setUserPhone("0916016987");
        user.setUserHash("123"); ;
        user.setUserFullName("Sam Smith");
        user.setUserCreatedOn(new Date());
        user.setUserCreatedBy("danghiue");
        entityManager.persistAndFlush(user);

        User found = userRepository.findByEmail(user.getUserEmail());

        assertThat(found.getUserEmail()).isEqualTo(user.getUserEmail());
    }

    @Test
    public void findById() {
        User user = userRepository.findById(-1l).orElse(null);
        assertThat(user).isNull();
    }

    @Test
    public void create(){
        User user = new User();
        user.setUserEmail("sam.smith@gmail.com");
        user.setUserPhone("0916016987");
        user.setUserHash("123"); ;
        user.setUserFullName("Sam Smith");
        user.setUserCreatedOn(new Date());
        user.setUserCreatedBy("danghiue");

        assertThat(userRepository.save(user)).isEqualTo(user);
    }

    @Test
    public void search(){
        User user = new User();
        user.setUserEmail("sam.smith@gmail.com");
        user.setUserPhone("0916016987");
        user.setUserHash("123"); ;
        user.setUserFullName("Sam Smith");
        user.setUserCreatedOn(new Date());
        user.setUserCreatedBy("danghiue");

        User user1 = new User();
        user1.setUserEmail("sam.smith@gmail.com");
        user1.setUserPhone("0916016987");
        user1.setUserHash("123"); ;
        user1.setUserFullName("Sam Smith");
        user1.setUserCreatedOn(new Date());
        user1.setUserCreatedBy("danghiue");
        assertThat(userRepository.findUserByName(user.getUserEmail(),user.getUserFullName()).contains(user));
    }

}
