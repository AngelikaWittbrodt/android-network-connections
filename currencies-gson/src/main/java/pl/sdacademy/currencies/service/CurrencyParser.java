package pl.sdacademy.currencies.service;

import com.google.gson.Gson;

import org.json.JSONException;

import pl.sdacademy.currencies.domain.CurrencyTable;

public class CurrencyParser {

    private Gson gson;

    public CurrencyParser() {
        gson = new Gson();
    }

    public CurrencyTable parseCurrencyTable(String json) throws JSONException {
        CurrencyTable[] response = gson.fromJson(json, CurrencyTable[].class);
        return response[0];
    }
}
