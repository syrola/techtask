package com.zilch.task.demotask.dao;

import com.zilch.task.demotask.domain.User;
import com.zilch.task.demotask.domain.UserTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.zilch.task.demotask.domain.TransactionType.GROSSERY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class UserDaoTest {
    @Autowired
    public UserDao userDao;

    @Test
    public void getUserById() {
        Optional<User> user = userDao.getUserById(1L);
        assertTrue(user.isPresent());
        User userOlga = user.get();
        assertEquals("olga.s@company.com", userOlga.getAddress());
        assertEquals("Olga Syrova", userOlga.getName());
        assertEquals(0, userOlga.getVersion());
    }

    @Test
    public void getUserByAddress() {
        Optional<User> user = userDao.getUserByEmailAddress("olga.s@company.com");
        assertTrue(user.isPresent());
        User userOlga = user.get();
        assertEquals("Olga Syrova", userOlga.getName());
        assertEquals(0, userOlga.getVersion());
        /*assertEquals(1, userOlga.getId());*/
    }

    @Test
    public void addNewUser() {
        userDao.addNewUser("natalia.s@company.com", "Natalia");
        Optional<User> user = userDao.getUserByEmailAddress("natalia.s@company.com");
        assertTrue(user.isPresent());
        User userNatalia = user.get();
        assertEquals("Natalia", userNatalia.getName());
        assertEquals(0, userNatalia.getVersion());
    }

    @Test
    public void addUserTransaction() {
        Optional<User> user = userDao.getUserByEmailAddress("olga.s@company.com");
        assertTrue(user.isPresent());
        User userOlga = user.get();
        assertEquals("Olga Syrova", userOlga.getName());
        assertEquals(0, userOlga.getVersion());
        UserTransaction userTransaction = new UserTransaction.UserTransactionBuilder().setAmount(BigDecimal.valueOf(23.4)).setExecutionDate(LocalDateTime.now())
                .setName("Payment").setType(GROSSERY).build();
        userDao.addUserTransaction(userTransaction, userOlga.getId());
        Long id = userOlga.getId();
        assertTrue(userDao.getUserById(id).isPresent());
        assertFalse(userDao.getUserById(id).get().getUserTransactions().isEmpty());
    }
}
