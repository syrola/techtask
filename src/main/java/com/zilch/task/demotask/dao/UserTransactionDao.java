package com.zilch.task.demotask.dao;

import com.zilch.task.demotask.domain.UserTransaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserTransactionDao {

    @PersistenceContext
    private Session session;


    public List<UserTransaction> getUserTransactionByUserId(Long userId) {
        String hql = "from UserTransaction " +
                "WHERE user.id = :userId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", userId);
        List<UserTransaction> result = query.list();
        System.out.println("Result " + result.toString());
        return result;
    }

}
