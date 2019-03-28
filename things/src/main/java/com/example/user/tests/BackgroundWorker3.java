package com.example.user.tests;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundWorker3 extends AsyncTask<String, Void, String> {
    Context context;
    BackgroundWorker backgroundWorker;
    AlertDialog alertDialog;
    public static String test, wallet_id, name;
    private ArrayList<String> exData;
    public static  int good3;
    private ProgressDialog progressDialog;
    String result3 = "";
    Product2Activity product2Activity;
    int i;



    public BackgroundWorker3(Context ctx) {
        context = ctx;
    }


    @Override
    public String doInBackground(String... params) {

        exData = new ArrayList<String>();
        String type = params[0];
        String login_url = "http://telecomt108.000webhostapp.com/vending/checkshop2.php";

        if (type.equals("check")) {

            try {
                String check_price = params[1];
                String check_product_id = params[2];
                String check_amount = params[3];
                String check_datetime = params[4];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("check_price", "UTF-8") + "=" + URLEncoder.encode(check_price, "UTF-8") + "&"
                        + URLEncoder.encode("check_product_id", "UTF-8") + "=" + URLEncoder.encode(check_product_id, "UTF-8") + "&"
                        + URLEncoder.encode("check_amount", "UTF-8") + "=" + URLEncoder.encode(check_amount, "UTF-8") + "&"
                        + URLEncoder.encode("check_datetime", "UTF-8") + "=" + URLEncoder.encode(check_datetime, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result3 += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if (result3 != "") {


                } else {






                }
                return result3;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

        }
        return null;
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {
        if (result != "") {

        } else {
            good3 = Product3Activity.minteger3;
            Product3Activity product3Activity = new Product3Activity();
            product3Activity.Control3();
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}



