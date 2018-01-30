package com.ikuba.ikubatest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.ikuba.ikubatest.Rest.ApiClient;
import com.ikuba.ikubatest.Rest.ApiInterface;


/**
 * Created by AFARHAN-PC on 1/23/2018.
 */

public class AppsApplication extends MultiDexApplication {

    private ApiInterface mAPIService;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static AppsApplication get(Context context) {
        return (AppsApplication) context.getApplicationContext();
    }

    public static AppsApplication create(Context context) {
        return AppsApplication.get(context);
    }

    public ApiInterface getAPIService() {
        if (mAPIService == null) mAPIService = ApiClient.getClient();

        return mAPIService;
    }

    public void setAPIService(ApiInterface peopleService) {
        mAPIService = peopleService;
    }
}
