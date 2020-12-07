package com.zilch.task.demotask.dao;


import com.zilch.task.demotask.domain.User;
import com.zilch.task.demotask.domain.UserTransaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.zilch.task.demotask.domain.TransactionType.GROSSERY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public class UserTransactionDaoTest {
    @Autowired
    public UserTransactionDao userTransactionDao;

    @Autowired
    public UserDao userDao;

    @Test
    public void getUserByAddress() {
        userDao.addNewUser("olga.s@newcompany.com", "Olga");
        Optional<User> user = userDao.getUserByEmailAddress("olga.s@newcompany.com");
        assertTrue(user.isPresent());
        User userOlga = user.get();
        assertEquals("Olga", userOlga.getName());
        assertEquals(0, userOlga.getVersion());

        UserTransaction userTransaction = new UserTransaction.UserTransactionBuilder().setAmount(BigDecimal.valueOf(23.4)).setExecutionDate(LocalDateTime.now())
                .setName("Payment").setType(GROSSERY).build();
        userDao.addUserTransaction(userTransaction, user.get().getId());
        List<UserTransaction> userTransactions = userTransactionDao.getUserTransactionByUserId(user.get().getId());
        assertFalse(userTransactions.isEmpty());

    }
}
