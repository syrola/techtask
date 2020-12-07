package com.zilch.task.demotask.converter;


import com.zilch.task.demotask.controller.UserTransactionConverter;
import com.zilch.task.demotask.controller.UserTransactionDto;
import com.zilch.task.demotask.domain.UserTransaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.zilch.task.demotask.domain.TransactionType.GROSSERY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTransactionConverterTest {

    @Test
    public void testConverter() {
        List<UserTransaction> userTransactions = new ArrayList<>();
        UserTransaction userTranasaction = new UserTransaction.UserTransactionBuilder().setAmount(new BigDecimal(23))
                .setExecutionDate(LocalDateTime.now()).setName("GROSSERY").setType(GROSSERY).build();

        userTransactions.add(userTranasaction);
        List<UserTransactionDto> userTransactionDtos = UserTransactionConverter.toUserTransactionDto(userTransactions);
        assertFalse(userTransactionDtos.isEmpty());
        assertFalse(Objects.isNull(userTransactionDtos.get(0)));
        UserTransactionDto userTransactionDto = userTransactionDtos.get(0);
        assertEquals(new BigDecimal(23), userTransactionDto.getAmount());
        assertEquals("GROSSERY", userTransactionDto.getName());
        assertEquals(GROSSERY.toString(), userTransactionDto.getType());
    }
}
