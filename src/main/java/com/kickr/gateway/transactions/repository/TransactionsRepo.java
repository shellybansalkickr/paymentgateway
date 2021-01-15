package com.kickr.gateway.transactions.repository;

import com.kickr.gateway.transactions.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions,Long> {
    @Query("SELECT u FROM Transactions u WHERE u.transactionTime>= ?1 and u.transactionTime< ?2")
    public List<Transactions> findByDate(Date startDate, Date endDate);
}

