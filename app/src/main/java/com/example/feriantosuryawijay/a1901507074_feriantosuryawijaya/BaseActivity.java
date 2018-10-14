package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity{
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    public void snack(View view, String message){

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void resetET(EditText[] editText){
        for(int i = 0; i < editText.length; i++){
            editText[i].setText("");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            case R.id.add:
                startActivity(new Intent(this,ModifyDollActivity.class));
                return true;
            case R.id.stores:
                return true;
            case R.id.logout:
                SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                sp.edit().clear().commit();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                Toast.makeText(this , "Logged out" , Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
