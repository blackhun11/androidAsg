package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tvRegisterLink;
    View view;
    static ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //1 dummy user
        if (userList.isEmpty())
            new User(1, "admin", "admin@admin.com","admin");
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin){

            view = findViewById(R.id.loginLayout);
            SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor ed=sp.edit();

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String name = "";
            boolean isExist = false;

            for (User user : userList) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    isExist = true;
                    name = user.getName();
                    break;
                }
            }

            if (email.isEmpty()){
                snack(view, "Email must be filled!");
            }else if(password.isEmpty()){
                snack(view, "Password must be filled!");
            }else if(!isExist){
                snack(view,"Invalid username / password");
            }else{
                ed.putString("Username", name);
                ed.commit();

                Intent intent = new Intent(this, ViewDollActivity.class);
                startActivity(intent);
                finish();
            }
        } else if(v == tvRegisterLink){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
