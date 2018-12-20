package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.content.SharedPreferences;
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
    UsersDB usersDB;
//    static ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usersDB = new UsersDB(this);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

//        init();
    }

//    private void init () {
//        //1 dummy user
//        if (userList.isEmpty())
//            userList.add(new User(1 , "admin" , "admin@admin.com" , "admin"));
//    }

    @Override
    public void onClick ( View v ) {
        if (v == btnLogin) {

            view = findViewById(R.id.loginLayout);
            SharedPreferences sp = getSharedPreferences("Login" , MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            String name = usersDB.authenticateUser(email, password);

//            for (User user : userList) {
//                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                    isExist = true;
//                    name = user.getName();
//                    break;
//                }
//            }
            /* validation based on Assignment Case */
            if (email.isEmpty()) {
                snack(view , "Email must be filled!");
            } else if (password.isEmpty()) {
                snack(view , "Password must be filled!");
            } else if (name.isEmpty()) {
                snack(view , "Invalid username / password");
            } else {
                /* save to sharepreferences */
                ed.putString("Username" , name);
                ed.apply();
                Toast.makeText(this , "Welcome, " + name , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this , ViewDollActivity.class);
                startActivity(intent);
                finish();
            }
        } else if (v == tvRegisterLink) {
            Intent intent = new Intent(this , RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
