package com.zilch.task.demotask.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerRestTemplateTest {
    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void testGet() {
        UserTransactionDto userTransactionDto = new UserTransactionDto();
        userTransactionDto.setAmount(new BigDecimal("23"));
        userTransactionDto.setName("GROSSERY");
        userTransactionDto.setEmail("olga.s@company.com");
        userTransactionDto.setType("GROSSERY");
        ResponseEntity responseEntity=restTemplate.withBasicAuth("user", "password")
                .postForEntity("/usertransaction", userTransactionDto, void.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testPost() {
        UserTransactionDto userTransactionDto = new UserTransactionDto();
        userTransactionDto.setAmount(new BigDecimal("23"));
        userTransactionDto.setName("GROSSERY");
        userTransactionDto.setEmail("olga.s@company.com");
        userTransactionDto.setType("GROSSERY");
        restTemplate.withBasicAuth("user", "password")
                .postForEntity("/usertransaction", userTransactionDto, void.class);
        ResponseEntity<UserTransactionDto[]> response = restTemplate.withBasicAuth("user", "password")
                .getForEntity("/usertransactions?email=olga.s@company.com", UserTransactionDto[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody().toString());
        assertTrue(response.getBody().length != 0);
        UserTransactionDto result = response.getBody()[0];

        assertEquals("23.00", result.getAmount().toString());
        assertEquals("GROSSERY", result.getType());
        assertEquals("GROSSERY", result.getName());
    }
}
