package com.zilch.task.demotask.dao;

import com.zilch.task.demotask.domain.User;
import com.zilch.task.demotask.domain.UserTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDao {
    Logger LOG = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private Session session;

    public Optional<User> getUserByEmailAddress(String emailAddress) {
        String hql = "from User " +
                "WHERE address = :address";
        Query query = session.createQuery(hql);
        query.setParameter("address", emailAddress);
        User result = (User) query.uniqueResult();
        Optional<User> user = Optional.ofNullable(result);
        user.ifPresent(r -> LOG.info("User by emailAddress=" + emailAddress + ":" + r.toString()));
        return user;
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> result = Optional.ofNullable(session.get(User.class, id));
        result.ifPresent(r -> LOG.info("User by id=" + id + ":" + r.toString()));
        return result;
    }

    public void addNewUser(String address, String name) {
        User user = new User(name, address);
        session.save(user);
        session.flush();
        LOG.info("User " + user.toString() + " is saved");
    }

    public void addUserTransaction(UserTransaction userTransaction, Long userId) {
        Optional<User> result = this.getUserById(userId);
        if (result.isPresent()) {

            User user = result.get();
            result.get().addUserTransaction(userTransaction);
            session.update(user);
            session.flush();
            LOG.info("addUserTransaction is done with: " + user.toString());
        }
    }

}
