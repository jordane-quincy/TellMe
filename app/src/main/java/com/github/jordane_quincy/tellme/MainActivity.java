package com.github.jordane_quincy.tellme;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;

    private Button btn;

    private WebView webView;


    String pathToSavedFile = Environment
            .getExternalStorageDirectory().toString()
            + "/test.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(thread == null) {
                //thread = new Thread(null, commRunnable, "threadTAG");
                //thread.start();
                //launchIntent();
                Log.d(TAG, "clic sur le bouton");
                checkIfFileDownloadIsNeeded();
                //rendu :
                webView.reload();
                //}
            }
        });

        webView = (WebView) findViewById(R.id.webView);

        //Activation du JS dans la web view
        webView.getSettings().setJavaScriptEnabled(true);

        //pas de cache (phase de debug quoi)
        webView.clearCache(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        // ajout des objets java accessible depuis la page web en js
        webView.addJavascriptInterface(new JsTestClass(this), "classeAndroid");

        webView.loadUrl("file://"+ pathToSavedFile);

    }

    private void checkIfFileDownloadIsNeeded() {
        boolean needToDownload = true;

        File savedFile = new File(pathToSavedFile);
        if(savedFile.exists()){
            Log.d(TAG, "html file already downloaded at : "+ pathToSavedFile);
            needToDownload = false;
        }

        if(needToDownload) {
            Log.d(TAG, "html need download");
            new DownloadFileFromURL().execute("https://raw.githubusercontent.com/jordane-quincy/TellMeWeb/master/TellMe.html");
        }
    }


    /**
     * Showing Dialog
     */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // path to save the download file
                Log.d(TAG, "pathToSavedFile : "+ pathToSavedFile);

                // Output stream
                OutputStream output = new FileOutputStream(pathToSavedFile);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

        }


    }

}