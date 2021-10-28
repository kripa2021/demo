package com.example.demo;

public class Currency {
    private int currencyNumber;
    private String sourceCurrency;
    private String targetCurrency;
    private double amountToConvert;
    public Currency(int currencyNumber, String sourceCurrency, String targetCurrency, double amountToConvert) {
        super();
        this.currencyNumber = currencyNumber;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amountToConvert = amountToConvert;
    }


    public int getCurrencyNumber() {
        return currencyNumber;
    }
    public void setCurrencyNumber(int currencyNumber) {
        this.currencyNumber = currencyNumber;
    }
    public String getSourceCurrency() {
        return sourceCurrency;
    }
    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }
    public String getTargetCurrency() {
        return targetCurrency;
    }
    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    public double getAmountToConvert() {
        return amountToConvert;
    }
    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }


}
