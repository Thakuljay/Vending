package com.example.user.tests;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.things.contrib.driver.pwmservo.Servo;


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
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    BackgroundWorker backgroundWorker;
    AlertDialog alertDialog;
    public static String test, wallet_id, name;
    private ArrayList<String> exData;
    private ProgressDialog progressDialog;
    public static  int good1;
    String result = "";
    Product1Activity product1Activity;
    int i;
    Timer t;
    Servo mServo;


    public BackgroundWorker(Context ctx) {
        context = ctx;
    }


    @Override
    public String doInBackground(String... params) {

        exData = new ArrayList<String>();
        String type = params[0];
        String login_url = "http://telecomt108.000webhostapp.com/vending/checkshop.php";

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
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                if (result != "") {


                } else {






                }
                return result;
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
             good1 = Product1Activity.minteger1;
                Product1Activity product1Activity = new Product1Activity();
                product1Activity.Control1();

        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    //    private void Controls(){
//        try {
//            mServo = new Servo("PWM0");
//            mServo.setPulseDurationRange(0.0, 2.0);
////            mServo.setPulseDurationRange(1, 1.5); // according to your servo's specifications
//            mServo.setAngleRange(mServo.getMinimumAngle(), mServo.getMaximumAngle());       // according to your servo's specifications
//            mServo.setEnabled(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        t = new Timer();
//        t.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (i <= 2) {
//                    try {
//                        NextMove();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    t.cancel();
//                }
//
//            }
//
//        }, 0, 950);
//    }
//
//    private void NextMove() throws IOException {
//
//        switch (i % 2) {
//            case 0:
//
//                mServo.setAngle(mServo.getMinimumAngle());
//                break;
//            case 1:
//                mServo.setAngle(90);
////                mServo.setAngle(mServo.getMaximumAngle());
//                break;
//
//
//        }
//        i++;
//
//    }

}


