package com.example.user.tests;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class AdminpageActivity extends Activity {
    private ArrayList<String> exData_id, exData_num,exData_remain,exData_sum,exData_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);
        getActionBar().hide();
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();
    }

    private void init() {
        exData_id = new ArrayList<String>();
        exData_num = new ArrayList<String>();
        exData_sum = new ArrayList<String>();
        exData_time = new ArrayList<String>();
        exData_remain= new ArrayList<String>();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    String user_admin = "vending";
                    String pass_admin = "vending";
                    String login_url = "https://telecomt108.000webhostapp.com/vending/vending.php";
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user_admin, "UTF-8")+ "&"
                            + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass_admin, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d("JSON Result", result.toString());

                    JSONObject jsonObject = new JSONObject(result.toString());
                    JSONArray exArray = jsonObject.getJSONArray("main");

                    for (int i = 0; i < exArray.length(); i++) {
                        JSONObject jsonObj = exArray.getJSONObject(i);
                        exData_id.add(jsonObj.getString("product_id"));
                        exData_num.add(jsonObj.getString("amount"));
                       exData_remain.add(jsonObj.getString("wallet_id"));
                        exData_sum.add(jsonObj.getString("total"));
                        exData_time.add(jsonObj.getString("timestamp"));

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ListView listView = (ListView) findViewById(R.id.listView);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);

            }
        }.execute();
    }

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exData_id.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ResourceAsColor", "ViewHolder"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView tv_table1 = (TextView) view.findViewById(R.id.tv_table1);
            TextView tv_table2 = (TextView) view.findViewById(R.id.tv_table2);
            TextView tv_table3 = (TextView) view.findViewById(R.id.tv_table3);
            TextView tv_table4 = (TextView) view.findViewById(R.id.tv_table4);
            TextView tv_table5 = (TextView) view.findViewById(R.id.tv_table5);

            tv_table1.setText(exData_id.get(i));
            tv_table2.setText(exData_num.get(i));
            tv_table3.setText(exData_remain.get(i));
            tv_table4.setText(exData_sum.get(i));
            tv_table5.setText(exData_time.get(i));


            return view;
        }
    }


}