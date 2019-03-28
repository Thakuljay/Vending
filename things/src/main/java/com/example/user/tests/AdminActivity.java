package com.example.user.tests;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity {
EditText PasswordEt;
Button btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getActionBar().hide();
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        PasswordEt = (EditText) findViewById(R.id.etPassword);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password55 = PasswordEt.getText().toString();
String password_vending = password55+"";
                    String type5 = "login";
                    String username = "vending";
//                    BackgroundloginAdmin backgroundloginAdmin = new BackgroundloginAdmin(getApplication());
////                    backgroundloginAdmin.execute(type5,password,username);
                if(password_vending.equals(username)){
                    Intent i = new Intent(AdminActivity.this, AdminpageActivity.class);
                    startActivity(i);
                    finish();


                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AdminActivity.this);
                    builder1.setMessage("Password wrong");
                    builder1.setCancelable(true);
                    builder1.show();


                }

            }
        });
    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >=3 ) {
            return true;
        }
        return false;
    }
    public static Activity fa;

    AdminActivity() {
        fa = this;
    }

}
