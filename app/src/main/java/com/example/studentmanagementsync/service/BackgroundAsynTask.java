package com.example.studentmanagementsync.service;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BackgroundAsynTask extends AsyncTask<Void, Void, String> {
    private String query;
    private URL url;
    private Callback callback;

    public BackgroundAsynTask(String query, String url, Callback callback) throws MalformedURLException {
        this.query = query;
        this.url = new URL(url);
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String responseData = "";
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            byte[] outputBytes = query.getBytes("UTF-8");

            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            OutputStream os = urlConnection.getOutputStream();
            os.write(outputBytes);
            os.close();

            int statusCode = urlConnection.getResponseCode();

            if (statusCode == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                responseData = convertStreamToString(inputStream);
            } else {
                responseData = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.onTaskCompleted(s);
    }

    public String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
