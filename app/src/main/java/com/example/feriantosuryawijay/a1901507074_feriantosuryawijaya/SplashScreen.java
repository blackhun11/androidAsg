package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sp = this.getSharedPreferences("Login" , MODE_PRIVATE);
        final String username = sp.getString("Username" , null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                /* if already login, go to Main Page, else redirect to Login Page by checking sharedpreferences*/
                if (username == null) {
                    startActivity(new Intent(SplashScreen.this , LoginActivity.class));
                } else {
                    Toast.makeText(SplashScreen.this , "Welcome, " + username , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashScreen.this , ViewDollActivity.class));
                }
                finish();
            }
        } , 2000);
    }

}
