package com.example.smartfactory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfactory.network.Callretrofit;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {
    static class Thread_temp extends Thread{
        public String response;
        public String id;
        public String pw;
        @Override
        public void run() {
            super.run();
            response = Callretrofit.post_login_request(id,pw);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button LoginButton = (Button) findViewById(R.id.btn_login);
        Button RegisterButton = (Button) findViewById(R.id.btn_register);
        EditText editTextID=findViewById(R.id.et_id);
        EditText editTextPw=findViewById(R.id.et_pass);
        LoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Thread_temp t= new Thread_temp();
                t.id=editTextID.getText().toString();
                t.pw=editTextPw.getText().toString();
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ID pw"+t.id+" "+t.pw);
                System.out.println("t.response"+t.response);
                if (t.response.equals("0")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user_id",editTextID.getText().toString());
                    Callretrofit.post_push_token(FirebaseMessaging.getInstance().getToken().getResult(),t.id);

                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 다시 확인해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}