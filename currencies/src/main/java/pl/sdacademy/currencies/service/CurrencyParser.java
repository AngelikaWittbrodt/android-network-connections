package pl.sdacademy.currencies.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pl.sdacademy.currencies.domain.CurrencyRate;
import pl.sdacademy.currencies.domain.CurrencyTable;

public class CurrencyParser {

    public CurrencyTable parseCurrencyTable(String string) throws JSONException {
        JSONArray array = new JSONArray(string);
        JSONObject jsonObject = array.getJSONObject(0);
        String table = jsonObject.getString("table");
        String number = jsonObject.getString("no");
        String effectiveDate = jsonObject.getString("effectiveDate");

        CurrencyTable currencyTable = new CurrencyTable();
        currencyTable.setTable(table);
        currencyTable.setNumber(number);
        currencyTable.setEffectiveDate(effectiveDate);

        JSONArray currenciesJson = jsonObject.getJSONArray("rates");
        List<CurrencyRate> currencies = parseRatesArray(currenciesJson);
        currencyTable.setCurrencies(currencies);
        return currencyTable;
    }

    private List<CurrencyRate> parseRatesArray(JSONArray array) {
        List<CurrencyRate> rates = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonRate = array.optJSONObject(i);
            String symbol = jsonRate.optString("code");
            String name = jsonRate.optString("currency");
            Double rate = jsonRate.optDouble("mid");

            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCode(symbol);
            currencyRate.setCurrency(name);
            currencyRate.setMid(rate);
            rates.add(currencyRate);
        }

        return rates;
    }
}
