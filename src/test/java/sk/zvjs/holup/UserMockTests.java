package sk.zvjs.holup;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import sk.zvjs.holup.user.UserController;
import sk.zvjs.holup.user.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserMockTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @Test
//    public void givenEmployees_whenGetEmployees_thenStatus200()
//            throws Exception {
//
//        mockMvc.perform(get("/api/v1/employees")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].name", is("bob")));
//    }
}
