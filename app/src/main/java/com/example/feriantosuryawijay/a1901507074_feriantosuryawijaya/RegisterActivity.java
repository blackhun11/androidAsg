package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.LoginActivity.userList;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    EditText etEmail,etName,etPassword,etPasswordConf;
    CheckBox cbxAgreement;
    Button btnRegister;
    TextView tvLoginLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConf = (EditText) findViewById(R.id.etPasswordConf);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        tvLoginLink = (TextView) findViewById(R.id.tvLoginLink);
        cbxAgreement = (CheckBox) findViewById(R.id.cbxAgreement);

        btnRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tvLoginLink){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else if(v == btnRegister){

            String name = "", email = "", password = "", passwordConf = "";
            int count = 0, alpha = 0, numeric = 0, id = 0;
            boolean isExist = false;
            View view = findViewById(R.id.registerLayout);

            name = etName.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            passwordConf = etPasswordConf.getText().toString().trim();

            for(int i = 0; i < email.length(); i++){
                if(email.charAt(i)  == '@'){
                    count++;
                }
            }

            for (int i = 0; i < password.length(); i++){
                if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){
                    numeric++;
                }

                if ((password.charAt(i) >= 'a' && password.charAt(i) <= 'z') || (password.charAt(i) >= 'A' && password.trim().charAt(i) <= 'Z' )) {
                    alpha++;
                }
            }

            for (User user : userList) {
                if (user.getEmail().equals(email)) {
                    isExist = true;
                    break;
                }
            }

            if (email.isEmpty()){
                snack(view, "Email must be filled!");
            }else if(count < 1 || count > 1){
                snack(view, "Email must contain one (@)");
            }else if(name.isEmpty()){
                snack(view, "Name must be filled!");
            }else if(password.isEmpty()){
                snack(view, "Password must be filled!");
            }else if(password.length() < 6){
                snack(view, "Password contains at least 6 characters!");
            }else if(alpha == 0 || numeric == 0){
                snack(view, "Password must consist at least one alphabet & one number!");
            }else if(passwordConf.isEmpty()){
                snack(view, "Password confirmation can't be empty!");
            }else if(!password.equals(passwordConf)){
                snack(view, "Password confirmation doesn't match with the password!");
            }else if(!cbxAgreement.isChecked()){
                snack(view, "You must agree the term & condition");
            }else if(isExist){
                snack(view, email + " already registered by the other user, please try with the other email!");
            }else{
                id = (userList.size() == 0) ? 1: userList.get(userList.size() - 1).getId() + 1;
                userList.add(new User(id, name, email, password));
                snack(view, "Success to register!");
                resetET(new EditText[]{etName, etEmail, etPassword, etPasswordConf});
                cbxAgreement.setChecked(false);
            }



        }
    }

}
