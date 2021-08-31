package com.alexnik.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();;
    private final String DEV_CODE = "nGUwRJeedzqJ7V2fuuAAb3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppsFlyerLib AppsFlyer = AppsFlyerLib.getInstance();
        Context context = this;

        AppsFlyer.setDebugLog(true);
        AppsFlyer.init(DEV_CODE, null, context);
        AppsFlyer.start(context);

        Map<String, Object> values = new HashMap<String, Object>();
        values.put(AFInAppEventParameterName.CONTENT_ID, "HelloWorld");
        values.put(AFInAppEventParameterName.REVENUE, 15);
        AppsFlyer.logEvent(context, AFInAppEventType.PURCHASE, values, new AppsFlyerRequestListener() {
            @Override
            public void onSuccess() {
                Log.d(LOG_TAG, "Event sent successfully");
            }

            @Override
            public void onError(int i, @NonNull String s) {
                Log.d(LOG_TAG, "Event failed to be sent:\n" +
                        "Error code: " + i + "\n"
                        + "Error description: " + s);
            }
        });
    }
}