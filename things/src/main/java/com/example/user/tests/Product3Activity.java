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

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class Product3Activity extends Activity {
    public static int minteger3=1;
    private Button btn_next3;
    private String wallet_id3, wallet_id_qr3;
    private String ids3,amount3,sum3 ;
    int price3;
    Servo mServo3;
    ImageView back3;
    private static final String GPIO_NAME = "BCM19";  // e.g. BCM20

    private static SoftPwm mPwm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product3);
        display(minteger3);
        getActionBar().hide();
        Button btn = findViewById(R.id.btn3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_next3 = findViewById(R.id.btn_next3);
        btn_next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = dateFormat.format(new Date()); // Find todays date

                // set the custom dialog components - text, image and button
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                // custom dialog
                final Dialog dialog = new Dialog(Product3Activity.this);
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
                wallet_id3 = "90001111";
                ids3 = "T063";
                amount3 ="0"+minteger3;
                sum3 = "0020";
                wallet_id_qr3 = "000201" + "010211" + "29370016A000000677010111011300000" + wallet_id3 + "5802TH" + "5303764" + ids3+amount3+sum3;
                price3 = minteger3*20;
                // set the custom dialog components - text, image and button
                TextView tvsum = (TextView) dialog.findViewById(R.id.tvsum);
                ImageView image = (ImageView) dialog.findViewById(R.id.dialog_imageview);
                tvsum.setText("ราคาสินค้า " +price3+ " บาท");
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(wallet_id_qr3, BarcodeFormat.QR_CODE, 300, 300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                // if button is clicked, close the custom dialog
                final String price = sum3;
                final String product_id = ids3;
                final String amount = amount3;
                final String type = "check";
                final String time = currentDateTime;
                Timer myTimer;
                myTimer = new Timer();

                myTimer.schedule(new TimerTask() {
                    public void run() {
                        // Do some thing
                        BackgroundWorker3 backgroundWorker3 = new BackgroundWorker3(getApplication());
                        backgroundWorker3.execute(type, price, product_id, amount, time);
                    }
                }, 0, 2000);
                dialog.show();

            }
        });
        back3 = findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void increaseInteger(View view) {
        if(minteger3<=4){
            minteger3 = minteger3 + 1;
            display(minteger3);
        }
        else {

        }


    }public void decreaseInteger(View view) {
        if(minteger3!=1){
            minteger3 = minteger3 - 1;
            display(minteger3);
        }
        else {

        }

    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number3);
        displayInteger.setText("" + number);
    }
    public void Control3(){
        try {
            mPwm = SoftPwm.openSoftPwm(GPIO_NAME);
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
            int round3 = BackgroundWorker3.good3;
            Thread.sleep(1800*round3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onDestroy();
    }

}
