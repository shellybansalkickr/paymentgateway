package com.kickr.gateway.transactions.controller;



import com.kickr.gateway.transactions.Transactions;
import com.kickr.gateway.transactions.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;




    @GetMapping("/showAllTransactions")
    public ResponseEntity<List<Transactions>> showAllTransactions(){
        try{
            List list=transactionsService.findAll();
            return new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error in transaction");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showTransaction/{id}")
    public ResponseEntity<Transactions> showTransactionByID(@PathVariable("id") String id){
        try{
            Transactions transactions=transactionsService.findByID(Long.parseLong(id)).get();
            return new ResponseEntity<Transactions>(transactions, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error in transaction");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveTransaction")
    public ResponseEntity<Transactions> savedTransaction(@RequestParam(name="cardNumber")String cardNumber,
                                                   @RequestParam(name="cvv")String cvv,
                                                   @RequestParam(name="expDate")String expDate,
                                                   @RequestParam(name="mobile") String mobile,
                                                   @RequestParam(name="email")String email,
                                                   @RequestParam(name="address")String address,
                                                   @RequestParam(name= "country") String country,
                                                   @RequestParam(name="amount")Float amount,
                                                   @RequestParam(name="cusName")String cusName
                                                   ) {
        Transactions transactions = new Transactions();
        transactions.setAddress(address);
        transactions.setAmount(amount);
        transactions.setCardNumber(cardNumber);
        transactions.setCountry(country);
        transactions.setCvv(Integer.getInteger(cvv));
        transactions.setExpDate(expDate);
        transactions.setCustName(cusName);
        transactions.setEmail(email);
        transactions.setMobile(mobile);
        try {
           Transactions successTrans= transactionsService.saveTransaction(transactions);
           return new ResponseEntity<>(successTrans,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }
    }



}
