package me.readeveloper.alarmdroid.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import me.readeveloper.alarmdroid.LoginScreenActivity;

public class HttpRequestClient {
    private Context context;
    private String URL;
    private HashMap<String, String> parameters = new HashMap<>();
    private HashMap<String, String> httpHeaders = new HashMap<>();

    private RequestQueue queue;

    public HttpRequestClient(String url, Context context) {
        this.URL = url;
        this.context = context;

        this.queue = Volley.newRequestQueue(this.context);
    }

    public HttpRequestClient setHeader(String key, String value) {
        this.httpHeaders.put(key, value);
        return this;
    }

    public HttpRequestClient setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public HashMap<String, String> getHttpHeaders() {
        return this.httpHeaders;
    }

    public HashMap<String, String> getParameters() {
        return this.parameters;
    }

    public void post(Response.Listener responseHandler, Response.ErrorListener errorResponseHandler) {
        this.httpRequest(Request.Method.POST, responseHandler, errorResponseHandler);
    }

    public void get(Response.Listener responseHandler, Response.ErrorListener errorResponseHandler) {
        this.httpRequest(Request.Method.GET, responseHandler, errorResponseHandler);
    }

    public void delete(Response.Listener responseHandler, Response.ErrorListener errorResponseHandler) {
        this.httpRequest(Request.Method.DELETE, responseHandler, errorResponseHandler);
    }

    public void put(Response.Listener responseHandler, Response.ErrorListener errorResponseHandler) {
        this.httpRequest(Request.Method.PUT, responseHandler, errorResponseHandler);
    }

    private void httpRequest(int httpMethod, Response.Listener responseHandler, Response.ErrorListener errorResponseHandler) {
        StringRequest stringRequest = new StringRequest(
                httpMethod, this.URL, responseHandler, errorResponseHandler
        ) {
            @Override
            protected Map<String,String> getParams(){
                return getParameters();
            }

            @Override
            public Map<String, String> getHeaders() {
                return getHttpHeaders();
            }
        };

        this.queue.add(stringRequest);
    }
}
