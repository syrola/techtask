package com.zilch.task.demotask.controller;

import com.zilch.task.demotask.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
/*@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        *//*classes = WebSecurityConfig.class*//*
)*/
@WebMvcTest(UserTransactionController.class)
public class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;


    @Test
    @WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
    public void testGetUserTransaction() throws Exception {
        this.mockMvc.perform(get("/usertransactions?email=olga.s@company.com")
        )
                .andExpect(status().isOk())
        ;

              /*  .andReturn(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect()*/

    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
    public void testAddUserTransaction() throws Exception {
        UserTransactionDto userTransactionDto = new UserTransactionDto();
        userTransactionDto.setAmount(new BigDecimal("23"));
        userTransactionDto.setName("GROSSERY");
        userTransactionDto.setEmail("olga.s@company.com");
        userTransactionDto.setType("GROSSERY");
        String s= "{\n" +
                "        \"id\":\"3\",\n" +
                "        \"type\":\"GROSSERY\",\n" +
                "        \"name\":\"GROSSERY\",\n" +
                "\"executionDate\": \"wew\",\n" +
                "\"email\":\"olga.s@company.com\",\n" +
                "  \"amount\":\"34\"\n" +
                "}";
        this.mockMvc.perform(post("/usertransaction")
                .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(s))
                .andExpect(status().isOk());

              /*  .andReturn(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect()*/

    }
}
