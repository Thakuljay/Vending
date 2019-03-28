package com.example.user.tests;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.things.contrib.driver.pwmservo.Servo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.apache.commons.net.time.TimeTCPClient;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Product1Activity extends Activity {
    public static int minteger1=1;
    Servo mServo1;
    int i;
    Timer t;
    String Ts;
    ImageView back1;
    private Button btn_next1;
    private String wallet_id1, wallet_id_qr1;
    private String ids1,amount1,sum1 ;
    int price1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product1);
        getActionBar().hide();
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        display(minteger1);
        btn_next1 = findViewById(R.id.btn_next1);
        btn_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final String currentDateTime = dateFormat.format(new Date()); // Find todays date




                // set the custom dialog components - text, image and button
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                // custom dialog
                final Dialog dialog = new Dialog(Product1Activity.this);
                dialog.setContentView(R.layout.sample);
                dialog.setTitle(currentDateTime);
                final TextView tvtimer = dialog.findViewById(R.id.tvtimer);
                CountDownTimer countDownTimer = new CountDownTimer(90*1000,1000) {
                    @Override
                    public void onTick(long l) {
                        tvtimer.setText("โปรดทำรายการภายใน : "+l/1000+" วินาที");
                    }

                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                    }
                }.start();
                wallet_id1 = "90001111";
                ids1 = "T061";
                amount1 ="0"+minteger1;
                sum1 = "0010";
                wallet_id_qr1 = "000201" + "010211" + "29370016A000000677010111011300000" + wallet_id1 + "5802TH" + "5303764" + ids1+amount1+sum1;
                price1 = minteger1*10;
                // set the custom dialog components - text, image and button
                TextView tvsum = (TextView) dialog.findViewById(R.id.tvsum);
                ImageView image = (ImageView) dialog.findViewById(R.id.dialog_imageview);
                tvsum.setText("ราคาสินค้า " +price1+ " บาท");
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(wallet_id_qr1, BarcodeFormat.QR_CODE, 300, 300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                // if button is clicked, close the custom dialog

// Hide after some seconds
//                final Handler handler  = new Handler();
//                final Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        if (dialog.isShowing()) {
//                            dialog.dismiss();
//                        }
//                    }
//                };
//
//                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        handler.removeCallbacks(runnable);
//                    }
//                });
//
//                handler.postDelayed(runnable, 10000);


                final String price = sum1;
                final String product_id = ids1;
                final String amount = amount1;
                final String type = "check";
                final String time = currentDateTime;
                Timer myTimer;
                myTimer = new Timer();

                myTimer.schedule(new TimerTask() {
                    public void run() {
                        // Do some thing
                        BackgroundWorker backgroundWorker = new BackgroundWorker(getApplication());
                        backgroundWorker.execute(type, price, product_id, amount, time);
                    }
                }, 0, 2000);

                dialog.show();
            }
        });
        back1 = findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void increaseInteger(View view) {
        if(minteger1<=4){
            minteger1 = minteger1 + 1;
            display(minteger1);
        }
        else {

        }


    }public void decreaseInteger(View view) {
        if(minteger1!=1){
            minteger1 = minteger1 - 1;
            display(minteger1);
        }
        else {

        }

    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number1);
        displayInteger.setText("" + number);
    }
    public void Control1(){

            try {
                mServo1 = new Servo("PWM0");
                mServo1.setPulseDurationRange(1.5, 2); // according to your servo's specifications
                mServo1.setAngleRange(0, 90);
                // according to your servo's specifications
                mServo1.setEnabled(true);
            } catch (IOException e) {
                // couldn't configure the servo...
            }


// Make the servo move:

            try {
                mServo1.setAngle(30);
            } catch (IOException e) {
                // error setting servo
            }

            try {
                int round1 = BackgroundWorker.good1;
                Thread.sleep(2000*round1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

// Close the servo when finished:

            try {
                mServo1.setAngle(0);
                mServo1.close();
                onDestroy();
            } catch (IOException e) {
                // error closing servo
            }
        }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}