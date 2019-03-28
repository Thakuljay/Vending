package com.example.user.tests;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.contrib.driver.pwmservo.Servo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ControlServo  {
    Servo mServo;
    int i;
    Timer t;
Servo servo;








public void control1() {
    try {
        mServo = new Servo("PWM0");
        mServo.setPulseDurationRange(0.0, 2.0);
//            mServo.setPulseDurationRange(1, 1.5); // according to your servo's specifications
        mServo.setAngleRange(0.0, 90.0);       // according to your servo's specifications
        mServo.setEnabled(true);
    } catch (IOException e) {
        e.printStackTrace();
    }
    t = new Timer();
    t.schedule(new TimerTask() {
        @Override
        public void run() {
            if (i <= 4) {
                try {
                    NextMove();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                t.cancel();
            }

        }

    }, 0, 950);

}









    private void NextMove() throws IOException {

        switch (i % 2) {
            case 0:

                mServo.setAngle(0.0);
                break;
            case 1:
                mServo.setAngle(90.0);
//                mServo.setAngle(mServo.getMaximumAngle());
                break;


        }
        i++;

    }



}



