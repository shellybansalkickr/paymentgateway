package com.kickr.gateway.transactions.service;

import com.kickr.gateway.transactions.Transactions;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ITransactionsService {
    List<Transactions> findAll();

    Optional<Transactions> findByID(Long Id);

    Transactions saveTransaction(Transactions transactions);

    List<Transactions> findByDate(Date startDate, Date endDate);
}
