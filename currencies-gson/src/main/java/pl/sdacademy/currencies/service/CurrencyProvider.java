package pl.sdacademy.currencies.service;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import pl.sdacademy.currencies.domain.CurrencyTable;

public class CurrencyProvider extends AsyncTask<String, Void, CurrencyTable> {

    private final static String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/tables/{table}/?format=json";
    private final static String NBP_TABLE_PLACEHOLDER = "{table}";

    private CurrencyAsyncTaskListener listener;
    private CurrencyParser currencyParser;

    public CurrencyProvider(CurrencyAsyncTaskListener listener) {
        this.listener = listener;
        currencyParser = new CurrencyParser();
    }

    @Override
    protected CurrencyTable doInBackground(String... params) {
        try {
            String tableParam = params[0];
            String json = getJson(tableParam);
            return currencyParser.parseCurrencyTable(json);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(CurrencyTable result) {
        listener.onDataLoaded(result);
    }

    private String getJson(String table) throws IOException {
        String urlString = NBP_API_URL.replace(NBP_TABLE_PLACEHOLDER, table);
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = new BufferedInputStream(connection.getInputStream());

        String json = IOUtils.toString(inputStream, Charset.forName("UTF-8").name());
        connection.disconnect();

        return json;
    }

    public interface CurrencyAsyncTaskListener {
        void onDataLoaded(CurrencyTable currencyTable);
    }
}
