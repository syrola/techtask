package com.zilch.task.demotask.controller;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserTransactionDto implements Serializable {
    private Long id;
    private String type;
    private String name;
    private String executionDate;
    private String email;
    private BigDecimal amount;

    public UserTransactionDto() {
    }

    public UserTransactionDto(Long id, String type, String name, String executionDate, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.executionDate = executionDate;
        this.email = "";
        this.amount = amount;
    }

    public UserTransactionDto(Long id, String type, String name, String executionDate, String email, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.executionDate = executionDate;
        this.email = email;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
