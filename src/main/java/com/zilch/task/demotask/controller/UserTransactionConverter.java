package com.zilch.task.demotask.controller;

import com.zilch.task.demotask.domain.UserTransaction;

import java.util.List;
import java.util.stream.Collectors;

public class UserTransactionConverter {


    public static List<UserTransactionDto> toUserTransactionDto(List<UserTransaction> userTransactions) {
        return userTransactions.stream()
                .map(u -> new UserTransactionDto(u.getId(), u.getType().toString(), u.getName(),
                        u.getExecutionDate().toString(), u.getAmount())).collect(Collectors.toList());
    }
}
