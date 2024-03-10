package org.javalabs.lab1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SwaggerConfig.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class SwaggerConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSwaggerDocumentation() throws Exception {
        mockMvc.perform(get("/v2/api-docs"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSwaggerUI() throws Exception {
        mockMvc.perform(get("/swagger-ui/index.html#/"))
                .andExpect(status().isNotFound());
    }
}