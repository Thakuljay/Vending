package com.example.user.tests;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.things.contrib.driver.pwmservo.Servo;
import com.google.android.things.pio.Pwm;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.leinardi.android.things.pio.SoftPwm;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Product4Activity extends Activity {
    public static int minteger4 = 1;
    private Button btn_next4;
    private String wallet_id4, wallet_id_qr4;
    private String ids4,amount4,sum4 ;
    int price4;
    Servo mServo4;
    ImageView back4;
    private static final String GPIO_NAME1 = "BCM26";  // e.g. BCM20

    private SoftPwm mPwm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product4);
        display(minteger4);
        getActionBar().hide();
        Button btn = findViewById(R.id.btn4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_next4 = findViewById(R.id.btn_next4);
        btn_next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = dateFormat.format(new Date()); // Find todays date

                // set the custom dialog components - text, image and button
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                // custom dialog
                final Dialog dialog = new Dialog(Product4Activity.this);
                dialog.setContentView(R.layout.sample);
                dialog.setTitle("Title...");
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
                wallet_id4 = "90001111";
                ids4 = "T064";
                amount4 ="0"+minteger4;
                sum4 = "0020";
                wallet_id_qr4 = "000201" + "010211" + "29370016A000000677010111011300000" + wallet_id4 + "5802TH" + "5303764" + ids4+amount4+sum4;
                price4 = minteger4*20;
                // set the custom dialog components - text, image and button
                TextView tvsum = (TextView) dialog.findViewById(R.id.tvsum);
                ImageView image = (ImageView) dialog.findViewById(R.id.dialog_imageview);
                tvsum.setText("ราคาสินค้า " +price4+ " บาท");
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(wallet_id_qr4, BarcodeFormat.QR_CODE, 300, 300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                // if button is clicked, close the custom dialog
                final String price = sum4;
                final String product_id = ids4;
                final String amount = amount4;
                final String type = "check";
                final String time = currentDateTime;
                Timer myTimer;
                myTimer = new Timer();

                myTimer.schedule(new TimerTask() {
                    public void run() {
                        // Do some thing
                        BackgroundWorker4 backgroundWorker4 = new BackgroundWorker4(getApplication());
                        backgroundWorker4.execute(type, price, product_id, amount, time);
                    }
                }, 0, 2000);
                dialog.show();

            }
        });
        back4 = findViewById(R.id.back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void increaseInteger(View view) {
        if(minteger4<=4){
            minteger4 = minteger4 + 1;
            display(minteger4);
        }
        else {

        }


    }public void decreaseInteger(View view) {
        if(minteger4!=1){
            minteger4 = minteger4 - 1;
            display(minteger4);
        }
        else {

        }

    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number4);
        displayInteger.setText("" + number);
    }
    public void Control4(){
        try {
            mPwm = SoftPwm.openSoftPwm(GPIO_NAME1);
            initializePwm(mPwm);
        } catch (IOException e) {

        }
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (mPwm != null) {
            mPwm.close();
            mPwm = null;
        }
    }

    public void initializePwm(SoftPwm pwm) throws IOException {
        pwm.setPwmFrequencyHz(50);
        pwm.setPwmDutyCycle(25);

        // Enable the PWM signal
        pwm.setEnabled(true);
        try {
            int round4 = BackgroundWorker4.good4;
            Thread.sleep(1800*round4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onDestroy();
    }

}
