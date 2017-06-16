package pl.sdacademy.networkconnections;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText urlEdit;
    private Button goButton;
    private WebView webView;
    private Snackbar snackbar;

    private IntentFilter networkFilter;
    private BroadcastReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEdit = (EditText) findViewById(R.id.url_edit);
        goButton = (Button) findViewById(R.id.button_go);

        webView = (WebView) findViewById(R.id.webview);
        setUpWebView(webView);

        View view = findViewById(R.id.container);
        snackbar = Snackbar.make(view, R.string.no_connection_error, Snackbar.LENGTH_INDEFINITE);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEdit.getText().toString();
                openUrlOrShowErrorMessage(url);
            }
        });

        networkFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkReceiver = new NetworkBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkReceiver, networkFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView(WebView webView) {
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    private void openUrlOrShowErrorMessage(String url) {
        if (!URLUtil.isNetworkUrl(url)) {
            Toast.makeText(MainActivity.this, R.string.url_address_error, Toast.LENGTH_SHORT).show();
            return;
        }

        webView.loadUrl(url);
    }

    private class NetworkBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkTool.isConnected(context)) {
                goButton.setEnabled(true);
                if (snackbar.isShown()) {
                    snackbar.dismiss();
                }
            } else {
                goButton.setEnabled(false);
                snackbar.show();
            }
        }
    }
}
