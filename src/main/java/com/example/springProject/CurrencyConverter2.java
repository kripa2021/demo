package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

//simple core java class
@RestController
//  localhost:8080/currency
@RequestMapping("/currency") //top level annotation for isolation of URIs
public class CurrencyConverter2 {

    ArrayList<Currency> currencyList = new ArrayList<Currency>(); //data member

    public CurrencyConverter2() {

        Currency currency1 = new Currency(1,"USD","INR",5000);
        Currency currency2 = new Currency(2,"USD","EUR",6000);
        Currency currency3 = new Currency(3,"EUR","NEP",7000);
        Currency currency4 = new Currency(4,"NEP","USD",8000);
        Currency currency5 = new Currency(5,"JAP","INR",9000);
        Currency currency6 = new Currency(6,"USD","RUS",8000);
        Currency currency7 = new Currency(7,"USD","POL",7000);


        currencyList.add(currency1);
        currencyList.add(currency2);
        currencyList.add(currency3);
        currencyList.add(currency4);
        currencyList.add(currency5);
        currencyList.add(currency6);
        currencyList.add(currency7);


    }

    //simple core java method
    @RequestMapping("/get/{cid}") // <-- localhost:8080/currency/get/1
    public Currency convertTheCurrency(@PathVariable("cid") int currencyId) {

        for(Currency theCurrency : currencyList) {
            if(theCurrency.getCurrencyNumber() == currencyId) {
                return theCurrency;
            }
        }
        return null;
    }



    //simple core java method
    @RequestMapping("/get/{cid}/{source}") // <-- http://localhost:8080/currency/get/7/USD
    public Currency convertTheCurrency(@PathVariable("cid") int currencyId, @PathVariable("source") String sourceCurr) {

        for(Currency theCurrency : currencyList) {
            if(theCurrency.getCurrencyNumber() == currencyId && theCurrency.getSourceCurrency().equals(sourceCurr)  ) {
                return theCurrency;
            }
        }
        return null;
    }

    //simple core java method
    @RequestMapping("/getObj") // <-- localhost:8080/currency/getObj
    public Currency convertTheCurrency2(@RequestBody Currency currObj) {

        for(Currency theCurrency : currencyList) {
            if(theCurrency.getCurrencyNumber() == currObj.getCurrencyNumber()
                    && theCurrency.getSourceCurrency().equals(currObj.getSourceCurrency()) ) {
                return theCurrency;
            }
        }
        return null;
    }

    @RequestMapping("/getAll") // <-- localhost:8080/currency/getAll
    public List<Currency> convertTheCurrency() {

        return currencyList;
    }
    //simple core java method
    @PostMapping("/add") // <-- localhost:8080/currency/add
    public Currency addCrrency(@RequestBody Currency currObj) {
        System.out.println("addCurrency...");
        currencyList.add(currObj);
        return currObj;
    }
    @PutMapping("/update") // <-- localhost:8080/currency/add
    public Currency updateCurrency(@RequestBody Currency currObj) {//3 INR RUS
        System.out.println("updateCurrency...");
        // 1   2   3  4  5
        // U-E  U-I U-N U-R U-P
        //
        for(int i=0;i<currencyList.size();i++) {
            Currency theCurrency = currencyList.get(i); // the i th index
            if(theCurrency.getCurrencyNumber() == currObj.getCurrencyNumber() ) {
                currencyList.remove(i); //remove that i th object which is matched in the arraylist
                currencyList.add(i,currObj); //welcome this new object in the arraylist - currObj
            }
        }
        return currObj;
    }
    @DeleteMapping("/remove") // <-- localhost:8080/currency/add
    public Currency deleteCurrency(@RequestBody Currency currObj) {//3 INR RUS
        System.out.println("deletCurrency...");
        // 1   2   3  4  5
        // U-E  U-I U-N U-R U-P
        //
        for(int i=0;i<currencyList.size();i++) {
            Currency theCurrency = currencyList.get(i); // the i th index
            if(theCurrency.getCurrencyNumber() == currObj.getCurrencyNumber() ) {
                currencyList.remove(i); //remove that i th object which is matched in the arraylist
            }
        }
        return currObj;
    }

}
