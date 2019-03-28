package com.example.user.tests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends Activity {
    CardView slot1, slot2, slot3, slot4;
    String wallet_id, wallet_id_qr;
    ImageButton btn_admin;
    long lastDown;
    long lastDuration;
    String amount, sum;
    Context context;
    int minteger = 1;

    private int longClickDuration = 1000;
    private boolean isLongPress = false;
    //Rxjava
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        initInstances();
        getActionBar().hide();
        wallet_id = "90001111";
        wallet_id_qr = "000201" + "010211" + "29370016A000000677010111011300000" + wallet_id + "5802TH" + "5303764" + "000000000000";
        String valve1 = "T061";
        String valve2 = "T062";
        String valve3 = "T063";
        String valve4 = "T064";
        String price1 = "10";
        String price2 = "15";
        String price3 = "20";
        String price4 = "20";
    }

    private void initInstances() {
              btn_admin = (ImageButton) findViewById(R.id.btn_admin);

        TextView tvtitle = (TextView) findViewById(R.id.tvtitle);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/dancing.otf");
        tvtitle.setTypeface(custom_font);

        TextView tvslot1 = (TextView) findViewById(R.id.tvslot1);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/caviardreamsbold.ttf");
        tvslot1.setTypeface(custom_font1);
        tvslot1.setText("Dentyne Ice");

        TextView tvslot2 = (TextView) findViewById(R.id.tvslot2);
        tvslot2.setTypeface(custom_font1);
        tvslot2.setText("Dentyne Mint");

        TextView tvslot3 = (TextView) findViewById(R.id.tvslot3);
        tvslot3.setTypeface(custom_font1);
        tvslot3.setText("Dentyne Splash(R)");

        TextView tvslot4 = (TextView) findViewById(R.id.tvslot4);
        tvslot4.setTypeface(custom_font1);
        tvslot4.setText("Dentyne Splash(B)");

        TextView tvdes1 = (TextView) findViewById(R.id.tvdes1);
        tvdes1.setTypeface(custom_font1);
        tvdes1.setText("ราคา " + "10" + " บาท/ชิ้น");

        TextView tvdes2 = (TextView) findViewById(R.id.tvdes2);
        tvdes2.setTypeface(custom_font1);
        tvdes2.setText("ราคา " + "15" + " บาท/ชิ้น");

        TextView tvdes3 = (TextView) findViewById(R.id.tvdes3);
        tvdes3.setTypeface(custom_font1);
        tvdes3.setText("ราคา " + "20" + " บาท/ชิ้น");

        TextView tvdes4 = (TextView) findViewById(R.id.tvdes4);
        tvdes4.setTypeface(custom_font1);
        tvdes4.setText("ราคา " + "20" + " บาท/ชิ้น");


        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);

        slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Product1Activity.class);

                startActivity(intent);

            }
        });
        slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Product2Activity.class);
                startActivity(intent);


            }
        });
        slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Product3Activity.class);
                startActivity(intent);

            }
        });
        slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, Product4Activity.class);
                startActivity(intent);

            }
        });
        btn_admin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    isLongPress = true;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isLongPress) {
                                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                                startActivity(intent);
                                // set your code here
                                // Don't forgot to add <uses-permission android:name="android.permission.VIBRATE" /> to vibrate.
                            }
                        }
                    }, longClickDuration);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    isLongPress = false;
                }
                return true;
            }


        });
    }


    //Ctrl+O

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }


}
