package com.dquinones;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArtistContollerTest {

    @Autowired
    private MockMvc mockMvc;

    private Long artistId = null;

    @Test
    @Order(1)
    public void getAllArtist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/artist")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    public void createArtist() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/artist")
                        .content("{ \"name\" :\"Ter\", \"description\" : \"test definitiu\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        artistId = JsonPath.parse(response).read("$.id", Long.class);
    }

    @Test
    @Order(3)
    public void getArtistById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/artist/" + artistId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(4)
    public void deleteArtistById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artist/" + artistId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(5)
    public void getArtistByIdError() throws Exception {
        long artistIdMod = artistId + 100;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/artist/" + artistIdMod)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    @Order(6)
    public void createArtistError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/artist")
                        .content("{ \"name\" :\"david\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    public void deleteArtistByIdError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/artist/" + artistId))
                .andExpect(status().isNotFound());
    }

}
