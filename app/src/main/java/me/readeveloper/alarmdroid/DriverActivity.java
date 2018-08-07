package me.readeveloper.alarmdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import me.readeveloper.alarmdroid.utils.Auth;
import me.readeveloper.alarmdroid.utils.HttpClient;

public class DriverActivity extends AppCompatActivity {
    private String status = "detenido";
    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        this.webView = (WebView) findViewById(R.id.driverWebView);
        this.webView.setWebViewClient(new WebViewClient(){
            /*public boolean shouldOverrideUrlLoading(WebView view, String url){
                webView.loadUrl(url);
                return false; // then it is not handled by default action
            }*/
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed("admin", "admin");
            }
            public void onPageFinished(WebView view, String url) {
                view.scrollTo(0,100);
            }
        });
        this.webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        webView.clearCache(true);
        this.webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setFocusable(false);
        webView.setFocusableInTouchMode(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(1);
        webView.getScrollY();
        //webView.setHttpAuthUsernamePassword("localhost","","admin","admin");
        this.webView.loadUrl("http://alarmdroid.mybrickcom.com/");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Auth.check(this)) {
            Auth.redirectToLogin(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!this.status.equals("detenido")) {
            this.sendStatus("detenido");
        }
    }

    public void setStatus(View view) {
        switch (view.getId()) {
            case R.id.ControlAvanzar:
                this.status = "adelante";
                break;
            case R.id.ControlAtras:
                this.status = "atras";
                break;
            case R.id.ControlDer:
                this.status = "derecha";
                break;
            case R.id.ControlIzq:
                this.status = "izquierda";
                break;
            case R.id.ControlDetener:
                this.status = "detenido";
                break;
        }

        this.sendStatus(this.status);
    }

    private void sendStatus(final String status) {
        HttpClient http = new HttpClient("https://alarmdroid.herokuapp.com/api/users/robots/status", this);
        http.setHeader("Authorization", "Bearer " + Auth.getApiTokenFromSharedPreferences(this))
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .setHeader("Accept", "application/json")
                .setParameter("status", status);

        http.put(new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Toast.makeText(
                        DriverActivity.this,
                        "Estatus del robot ha cambiado a " + status,
                        Toast.LENGTH_SHORT
                ).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorResponse", "Error on request.", error);
            }
        });
    }
}
