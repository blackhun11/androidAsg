package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ViewDollDetailActivity extends BaseActivity implements View.OnClickListener {

    TextView tvName, tvDescription;
    ImageView ivImage;
    Button btnShare;
    String dollName, name;
    EditText etPhone;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_doll_detail);

        sharedPreferences = getSharedPreferences("Login" , MODE_PRIVATE);
        name = sharedPreferences.getString("Username" , "");
        /* set value from intent */
        Intent intent = getIntent();
        dollName = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        int image = intent.getIntExtra("image" , 0);

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        ivImage = findViewById(R.id.ivImage);
        btnShare = findViewById(R.id.btnShare);
        tvName.setText(dollName);
        tvDescription.setText(description);
        ivImage.setImageResource(image);
        etPhone = findViewById(R.id.etPhone);

        btnShare.setOnClickListener(this);
    }


    @Override
    public void onClick ( View v ) {
        if (v == btnShare) {
            String phone = etPhone.getText().toString().trim();

            if (phone.isEmpty()){
                Toast.makeText(this, "Fill your phone number!", Toast.LENGTH_SHORT).show();
            }else {
                String message = String.format("Hey, check this doll from BlueDoll! It's the %s and it's so awesome! - %s" , dollName , name);
                sendSMS(phone , message);
            }
        }
    }

    private void sendSMS ( String phone , String message ) {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS} , 1);
        } else {
            SmsManager manager = SmsManager.getDefault();

            manager.sendTextMessage(phone , null , message , null , null);
            Toast.makeText(this , "Sent!" , Toast.LENGTH_SHORT).show();
        }
    }
}
