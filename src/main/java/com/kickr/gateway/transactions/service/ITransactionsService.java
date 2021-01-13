package com.kickr.gateway.transactions.service;

import com.kickr.gateway.transactions.Transactions;

import java.util.List;
import java.util.Optional;

public interface ITransactionsService {
    List<Transactions> findAll();

    Optional<Transactions> findByID(Long Id);

    Transactions saveTransaction(Transactions transactions);
}
