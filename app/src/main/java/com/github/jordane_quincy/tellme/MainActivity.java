package com.github.jordane_quincy.tellme;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private WebView webView;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if(thread == null) {
                    //thread = new Thread(null, commRunnable, "threadTAG");
                    //thread.start();
                    //launchIntent();
                    Log.d(TAG, "clic sur le bouton");
                //}
            }
        });

        //Activation du JS dans la web view
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/www/index.html");
    }


}
