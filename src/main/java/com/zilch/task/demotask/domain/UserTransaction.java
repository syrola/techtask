package com.zilch.task.demotask.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class UserTransaction {
    @Id
    @GeneratedValue(generator = "trans-seq-generator")
    @GenericGenerator(name = "trans-seq-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_trans_sequence"),
            })
    private Long id;

    @Column(updatable = false)
    private String name;

    @Column(updatable = false)
    private TransactionType type;

    @Column(updatable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(/*name = "execution_date", */columnDefinition = "TIMESTAMP")
    private LocalDateTime executionDate;

    @Version
    private Integer version;

    public UserTransaction() {
    }

    public UserTransaction(String name, TransactionType type, BigDecimal amount, LocalDateTime executionDate) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.executionDate = executionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTransaction)) return false;
        UserTransaction that = (UserTransaction) o;
        return Objects.equal(getName(), that.getName()) &&
                getType() == that.getType() &&
                Objects.equal(getAmount(), that.getAmount()) &&
                Objects.equal(getExecutionDate(), that.getExecutionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getType(), getAmount(), getExecutionDate());
    }

    @Override
    public String toString() {
        return "UserTransaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", executionDate=" + executionDate +
                ", version=" + version +
                '}';
    }

    public static class UserTransactionBuilder {
        private String name;
        private TransactionType type;
        private BigDecimal amount;
        private LocalDateTime executionDate;

        public UserTransactionBuilder() {
        }

        public UserTransactionBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserTransactionBuilder setType(TransactionType type) {
            this.type = type;
            return this;
        }

        public UserTransactionBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public UserTransactionBuilder setExecutionDate(LocalDateTime executionDate) {
            this.executionDate = executionDate;
            return this;
        }

        public UserTransaction build() {
            return new UserTransaction(name, type, amount, executionDate);
        }
    }
}