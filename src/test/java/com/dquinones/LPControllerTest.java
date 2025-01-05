package com.dquinones;

import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Long lpId = null;
    private String name;

    private String json = """
            {
              "name": "Test Mock",
              "description": "Album Mock",
              "artistId": 1,
              "songs": [
                {
                  "name": "Song 1992",
                  "authors": [1,5]
                },
                {
                  "name": "Song 1991",
                  "authors": [1, 5, 6]
                }
              ]
            }
            """;

    private String jsonErrorAuthor = """
            {
              "name": "Test Mock",
              "description": "Album Mock",
              "artistId": 1,
              "songs": [
                {
                  "name": "Song 1992",
                  "authors": [100]
                }
              ]
            }
            """;


    @Test
    @Order(1)
    public void getAllLp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lp")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    public void createLp() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/lp")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        lpId = JsonPath.parse(response).read("$.id", Long.class);
        name = JsonPath.parse(response).read("$.artist.name", String.class);

        System.out.println(name);
    }

    @Test
    @Order(3)
    public void getLpByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lp/artist")
                        .param("nameArtist", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    public void deleteLpById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lp/" + lpId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(5)
    public void getLpByNameError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lp/artist")
                        .param("nameArtist", name.concat("s"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(6)
    public void deleteLpByIdError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artist/" + lpId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    public void createLpAuthorError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/lp")
                        .content(jsonErrorAuthor)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }



}
