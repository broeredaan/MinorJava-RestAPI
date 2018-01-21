import com.ajp.yourgrade.controller.RestController;
import com.ajp.yourgrade.model.User;
import com.ajp.yourgrade.persistence.UserRepository;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{
        given(this.userRepository.findById(1)).willReturn(new User("test","test@test.nl",true,"test","eng"));
        this.mvc.perform(get("/v1/user?id=1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
