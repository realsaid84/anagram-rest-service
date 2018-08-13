package anagram.svc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnagramControllerTests {

    @Autowired
    private MockMvc mockMvc;
    

    @Test
    public void noParamAnagramShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/anagram")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.word").value("World"));
    }

    @Test
    public void paramAnagramShouldReturnAnagrams() throws Exception {
    ContentResultMatchers matcher = MockMvcResultMatchers.content();
        this.mockMvc.perform(get("/anagram/love"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(matcher.contentType("text/plain;charset=UTF-8"));
    }
    
    @Test
    public void requestBodyShouldAddWord() throws Exception {
        this.mockMvc.perform(post("/word")
        							.contentType(MediaType.APPLICATION_JSON)
        							.content("{ \"word\": \"emphiphany\"}"))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.word").value("emphiphany"));
    }
    
    @Test
    public void paramWordShouldDeleteWord() throws Exception {
    	ContentResultMatchers matcher = MockMvcResultMatchers.content();
        this.mockMvc.perform(delete("/word/emphiphany"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(matcher.string(("Deleted:emphiphany")));
    }
    
  

}
