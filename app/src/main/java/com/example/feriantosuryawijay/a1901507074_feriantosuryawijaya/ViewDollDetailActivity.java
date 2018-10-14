package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewDollDetailActivity extends BaseActivity {

    TextView tvName, tvDescription;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_doll_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id" ,0);
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        int image = intent.getIntExtra("image",0);

        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivImage = (ImageView) findViewById(R.id.ivImage);

        tvName.setText(name);
        tvDescription.setText(description);
        ivImage.setImageResource(image);

    }


}
