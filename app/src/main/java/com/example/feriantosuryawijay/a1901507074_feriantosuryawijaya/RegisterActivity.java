package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    EditText etEmail, etName, etPassword, etPasswordConf;
    CheckBox cbxAgreement;
    Button btnRegister;
    TextView tvLoginLink;
    UsersDB usersDB;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usersDB = new UsersDB(this);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConf = findViewById(R.id.etPasswordConf);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginLink = findViewById(R.id.tvLoginLink);
        cbxAgreement = findViewById(R.id.cbxAgreement);

        btnRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);

    }

    @Override
    public void onClick ( View v ) {
        if (v == tvLoginLink) {
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (v == btnRegister) {

            String name, email, password, passwordConf;
            int alpha = 0, numeric = 0;

            View view = findViewById(R.id.registerLayout);

            name = etName.getText().toString().trim();
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            passwordConf = etPasswordConf.getText().toString().trim();

            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                    numeric++;
                }

                if ((password.charAt(i) >= 'a' && password.charAt(i) <= 'z') ||
                        (password.charAt(i) >= 'A' && password.trim().charAt(i) <= 'Z')) {
                    alpha++;
                }
            }

            boolean isExist = usersDB.checkUserExists(email);

//            for (User user : userList) {
//                if (user.getEmail().equals(email)) {
//                    isExist = true;
//                    break;
//                }
//            }
            /* validation based on Assignment Case */
            if (email.isEmpty()) {
                snack(view , "Email must be filled!");
            } else if (email.lastIndexOf('@') != email.indexOf('@') || !email.contains("@")) {
                snack(view , "Email must contain one (@)");
            } else if (email.lastIndexOf('.') < email.indexOf('@')) {
                snack(view , "Email must contain a domain. ex('.com','.co.id')");
            } else if (email.indexOf('@') == email.indexOf('.') + 1
                    || email.indexOf('@') == email.indexOf('.') - 1) {
                snack(view , "(@) and (.) cannot be place beside each other");
            } else if (name.isEmpty()) {
                snack(view , "Name must be filled!");
            } else if (password.isEmpty()) {
                snack(view , "Password must be filled!");
            } else if (password.length() < 6) {
                snack(view , "Password contains at least 6 characters!");
            } else if (alpha == 0 || numeric == 0) {
                snack(view , "Password must consist at least one alphabet & one number!");
            } else if (passwordConf.isEmpty()) {
                snack(view , "Password confirmation can't be empty!");
            } else if (!password.equals(passwordConf)) {
                snack(view , "Password confirmation doesn't match with the password!");
            } else if (!cbxAgreement.isChecked()) {
                snack(view , "You must agree the term & condition");
            } else if (isExist) {
                snack(view , email + " already registered by the other user, please try with the other email!");
            } else {
//                int id = (userList.size() == 0) ?
//                        1 : userList.get(userList.size() - 1).getId() + 1;
//                userList.add(new User(id , name , email , password));

                usersDB.store(name , email , password);
                snack(view , "Success to register!");
                resetET(new EditText[]{etName , etEmail , etPassword , etPasswordConf});
                cbxAgreement.setChecked(false);
            }
        }
    }

}
