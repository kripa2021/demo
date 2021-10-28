package com.example.demo;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

//simple core java class
@RestController
public class CurrencyConverter {

    ArrayList<Currency> currencyList = new ArrayList<>(); //data member

    public CurrencyConverter() {

        Currency currency1 = new Currency(1, "USD", "INR", 5000);
        Currency currency2 = new Currency(2, "USD", "EUR", 6000);
        Currency currency3 = new Currency(3, "EUR", "NEP", 7000);
        Currency currency4 = new Currency(4, "NEP", "USD", 8000);
        Currency currency5 = new Currency(5, "JAP", "INR", 9000);
        Currency currency6 = new Currency(6, "USD", "RUS", 8000);
        Currency currency7 = new Currency(7, "USD", "POL", 7000);


        currencyList.add(currency1);
        currencyList.add(currency2);
        currencyList.add(currency3);
        currencyList.add(currency4);
        currencyList.add(currency5);
        currencyList.add(currency6);
        currencyList.add(currency7);


    }

    //simple core java method
    @RequestMapping("/convert/{cid}") // <-- localhost:8080/convert/1
    public Currency convertTheCurrency(@PathVariable("cid") int currencyId) {

        for (Currency theCurrency : currencyList) {
            if (theCurrency.getCurrencyNumber() == currencyId) {
                return theCurrency;
            }
        }
        return null;
    }

    //simple core java method
    @RequestMapping("/convert2") // <-- localhost:8080/convert2
    public Currency convertTheCurrency2(@RequestBody Currency currObj) {

        for (Currency theCurrency : currencyList) {
            if (theCurrency.getCurrencyNumber() == currObj.getCurrencyNumber()
                    && theCurrency.getSourceCurrency().equals(currObj.getSourceCurrency())) {
                return theCurrency;
            }
        }
        return null;
    }

    ///////////////////////////////////////
    //simple core java method
    @RequestMapping("/convert3") // <-- localhost:8085/convert3/1/USD
    public Currency convertTheCurrency3(@RequestBody Currency currObj) {

        for (Currency theCurrency : currencyList) {
            if (theCurrency.getCurrencyNumber() == currObj.getCurrencyNumber()
                    && theCurrency.getSourceCurrency().equals(currObj.getSourceCurrency())) {
                return theCurrency;
            }
        }
        return null;
    }

    @PostMapping("/add") // <-- localhost:8085/convert3/1/USD
    public Currency addCurrency3(@RequestBody Currency currObj) {
        System.out.println("Add currency...");
        currencyList.add(currObj);
        return currObj;
    }
}
