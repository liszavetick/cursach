package com.englishschool.controller;

import com.englishschool.repo.PurchasedRepo;
import com.englishschool.repo.SubsRepo;
import com.englishschool.repo.TeachersRepo;
import com.englishschool.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
public class OnlineCourseContTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubsRepo subsRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PurchasedRepo purchasedRepo;

    @Autowired
    private TeachersRepo teachersRepo;
    private OnlineCourseCont onlineCourseCont;



    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "USER")
    public void testSubsSearch() throws Exception {
        // Arrange
        // Assuming you have some data in the database, adjust this according to your actual data
        // For example, subsRepo.save(new Subs("English Course", "file.jpg", "General", (byte) 10, "2 months", "Intermediate", "Description"));

        // Act and Assert
        mockMvc.perform(post("/subs/search")
                        .param("name", "English")
                        .param("category", "General")
                        .param("sort", "asc"))
                .andExpect(status().isOk())
                .andExpect(view().name("subs"))
                .andExpect(MockMvcResultMatchers.model().attributeExists(/* Add expected model attributes here */));
    }
    @Test
    public void testSubs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/subs"))
                .andExpect(status().isOk())
                .andExpect(view().name("subs"));
    }
}
