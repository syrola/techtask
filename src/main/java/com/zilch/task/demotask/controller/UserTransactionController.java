package com.zilch.task.demotask.controller;

import com.zilch.task.demotask.domain.UserTransaction;
import com.zilch.task.demotask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zilch.task.demotask.controller.UserTransactionConverter.toUserTransactionDto;


@RestController
public class UserTransactionController {
    Logger LOG = LoggerFactory.getLogger(UserTransactionController.class);
    private UserService userService;

    @Autowired
    public UserTransactionController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/usertransactions")
    public UserTransactionDto[] getUserTransactions(@RequestParam("email") String address) {
        List<UserTransaction> userTransactions = userService.getAllUserTransactionForUserId(address);
        LOG.info("/usertransactions output:" + userTransactions.toString());
        List<UserTransactionDto> transactions = toUserTransactionDto(userTransactions);
        UserTransactionDto[] userTransactionsArray = new UserTransactionDto[transactions.size()];
        return transactions.toArray(userTransactionsArray);
    }

    @PostMapping("/usertransaction")
    public void addUserTransactions(@RequestBody UserTransactionDto dto) {
        userService.addUserTransactionForUserId(dto.getEmail(), dto.getAmount(), dto.getName(), dto.getType());
    }
}
