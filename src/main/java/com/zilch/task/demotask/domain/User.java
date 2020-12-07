package com.zilch.task.demotask.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "user-seq-generator")
    @GenericGenerator(name = "user-seq-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name="initial_value", value = "10")
            })
    private Long id;
    private String name;
    private String address;
    @Version
    private Integer version;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTransaction> userTransactions = new HashSet<>();

    public User() {
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<UserTransaction> getUserTransactions() {
        return userTransactions;
    }

    public void addUserTransaction(UserTransaction userTransaction) {
        userTransactions.add(userTransaction);
        userTransaction.setUser(this);
    }

    public void removeUserTransaction(UserTransaction userTransaction) {
        userTransactions.remove(userTransaction);
        userTransaction.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equal(getName(), user.getName()) &&
                Objects.equal(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getAddress());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                '}';
    }


}
