package com.zilch.task.demotask.service;

import com.zilch.task.demotask.dao.UserDao;
import com.zilch.task.demotask.dao.UserTransactionDao;
import com.zilch.task.demotask.domain.TransactionType;
import com.zilch.task.demotask.domain.User;
import com.zilch.task.demotask.domain.UserTransaction;
import com.zilch.task.demotask.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao;

    private UserTransactionDao userTransactionDao;

    @Autowired
    public UserService(UserDao userDao, UserTransactionDao userTransactionDao) {
        this.userDao = userDao;
        this.userTransactionDao = userTransactionDao;
    }

    public List<UserTransaction> getAllUserTransactionForUserId(String emailAddress) {
        Optional<User> user = userDao.getUserByEmailAddress(emailAddress);
        List<UserTransaction> userTransactions;
        if (user.isPresent()) {
            userTransactions = userTransactionDao.getUserTransactionByUserId(user.get().getId());
        } else {
            String error = String.format("The user not found with emailAddress= %s", emailAddress);
            LOG.error(error);
            throw new UserNotFoundException(error);
        }
        return userTransactions;
    }

    public void addUserTransactionForUserId(String emailAddress, BigDecimal amount,
                                            String name,
                                            String type) {
        Optional<User> user = userDao.getUserByEmailAddress(emailAddress);
        UserTransaction userTransaction = new UserTransaction.UserTransactionBuilder()
                .setType(TransactionType.valueOf(type))
                .setName(name)
                .setExecutionDate(LocalDateTime.now())
                .setAmount(amount)
                .build();
        user.ifPresent(u -> userDao.addUserTransaction(userTransaction, u.getId()));

    }
}
