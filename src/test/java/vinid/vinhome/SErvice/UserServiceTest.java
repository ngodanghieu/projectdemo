package vinid.vinhome.SErvice;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import vinid.vinhome.entities.User;
import vinid.vinhome.repository.IUserRepository;
import vinid.vinhome.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @MockBean
    private IUserRepository userRepository;

    @BeforeTestClass
    public void setUp() throws ParseException {
        MockitoAnnotations.initMocks(this);

        User user = new User();
        user.setUserEmail("sam.smith@gmail.com");
        user.setUserPhone("0916016987");
        user.setUserHash("123"); ;
        user.setUserFullName("Sam Smith");
        user.setUserCreatedOn(new Date());
        user.setUserCreatedBy("danghiue");


        Mockito.when(userRepository.findByEmail(user.getUserEmail())).thenReturn(user);
    }
}
