package sk.zvjs.holup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sk.zvjs.holup.user.AuthDTO;
import sk.zvjs.holup.user.UserController;
import sk.zvjs.holup.user.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class AuthTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void authUser() throws Exception {
        mockMvc.perform(post("/api/v1/auth")
        .content(new ObjectMapper().writeValueAsString(new AuthDTO("samodubovec@gmail.com", "samo123")))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
        );
//                .andExpect(jsonPath("$[0].name", is("bob")));
    }
}
