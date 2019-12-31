package vinid.vinhome.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MockMvc;
import vinid.vinhome.VinhomeApplication;
import vinid.vinhome.entities.User;
import vinid.vinhome.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = VinhomeApplication.class)
@AutoConfigureMockMvc
public class BaitapControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;


    @Test
    public void getUserById() throws Exception {
        createTestUser();

        mvc.perform(get("/api/baitap/get-user-by-id/?id=11").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.useremail", is("sam@gmail.com")));
    }

    private void createTestUser() throws ParseException {
        User user = new User();
        user.setUserEmail("sam.smith@gmail.com");
        user.setUserPhone("0916016987");
        user.setUserHash("123"); ;
        user.setUserFullName("Sam Smith");
        user.setUserCreatedOn(new Date());
        user.setUserCreatedBy("danghiue");
    }
}
