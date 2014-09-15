package com.datang.olv.propaganda;

import android.app.Application;

import com.datang.olv.propaganda.HttpClient.HttpClientInterface;

/**
 * Created by l on 14-9-14.
 */
public class OlvApplication extends Application{
    private HttpClientInterface.Schoolinfo gschoolinfo;

    public String getHttpClientToken() {
        return httpClientToken;
    }

    public void setHttpClientToken(String httpClientToken) {
        this.httpClientToken = httpClientToken;
    }

    private String httpClientToken;
    @Override
    public void onCreate(){
        super.onCreate();
        httpClientToken = null;
    }

    public HttpClientInterface.Schoolinfo getGschoolinfo() {
        return gschoolinfo;
    }

    public void setGschoolinfo(HttpClientInterface.Schoolinfo gschoolinfo) {
        this.gschoolinfo = gschoolinfo;
    }
}
