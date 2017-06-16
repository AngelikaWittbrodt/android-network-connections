package pl.sdacademy.currencies.domain;

import java.util.List;

public class CurrencyTable {

    private String table;

    private String number;

    private String effectiveDate;

    private List<CurrencyRate> currencies;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<CurrencyRate> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyRate> currencies) {
        this.currencies = currencies;
    }
}
