package com.kickr.gateway.transactions.controller;



import com.fasterxml.jackson.annotation.JsonAlias;
import com.kickr.gateway.transactions.Transactions;
import com.kickr.gateway.transactions.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;




   @GetMapping("/showTransactionByDate")
    public ResponseEntity<List<Transactions>> showAllTransactions(@RequestParam(name="date") String date){
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            // you can change format of date
            Date date1 = formatter.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date2 = formatter.parse((formatter.format(calendar.getTime())));

            List list=transactionsService.findByDate(date1,date2);
            return new ResponseEntity<List<Transactions>>(list, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error in transaction");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showTransaction")
    public ResponseEntity<Transactions> showTransactionByID(@RequestParam(name="id") String id){
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
                                                      @RequestParam(name="cvv")Integer cvv,
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
        transactions.setCvv(cvv);
        transactions.setExpDate(expDate);
        transactions.setCustName(cusName);
        transactions.setEmail(email);
        transactions.setMobile(mobile);
        try {
           transactionsService.saveTransaction(transactions);
           Transactions transactions1 = new Transactions(transactions.getId(),transactions.getStatus(),transactions.getAmount(),
                                                transactions.getTransactionTime(),transactions.getCountry());
           if(transactions1.getCountry().equals("USA")){
               transactions1.setCountry("USD");
           }
           else transactions1.setCountry("GMP");
           return new ResponseEntity<>(transactions1,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        }

        }






}
