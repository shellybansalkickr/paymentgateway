package com.kickr.gateway.transactions.service;

import com.kickr.gateway.transactions.Transactions;
import com.kickr.gateway.transactions.repository.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService implements ITransactionsService {
    @Autowired
    private TransactionsRepo transactionsRepo;

    @Override
    public List<Transactions> findAll() {
        List<Transactions> trans = (List<Transactions>)transactionsRepo.findAll();
        return trans;
    }

    @Override
    public Optional<Transactions> findByID(Long Id){
       return transactionsRepo.findById(Id);

    }

    @Override
    public Transactions saveTransaction(Transactions transactions) {
        return transactionsRepo.save(transactions);
    }

}
