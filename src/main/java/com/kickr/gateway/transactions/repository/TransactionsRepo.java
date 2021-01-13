package com.kickr.gateway.transactions.repository;

import com.kickr.gateway.transactions.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends CrudRepository<Transactions,Long> {
}

