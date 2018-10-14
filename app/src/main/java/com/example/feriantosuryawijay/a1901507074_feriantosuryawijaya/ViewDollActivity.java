package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ViewDollActivity extends BaseActivity {

    private ListView lvDoll;
    private DollAdapter dAdapter;

    static ArrayList<Image> imageList = new ArrayList<>();
    static ArrayList<Doll> dollList = new ArrayList<>();

    @Override
    protected void onResume () {
        super.onResume();
        dAdapter = new DollAdapter(this,dollList);
        lvDoll.setAdapter(dAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doll);

        lvDoll = (ListView) findViewById(R.id.lvDoll);

        //append spinner
        if (imageList.isEmpty()) {
            imageList.add(new Image(R.drawable.grizzly_doll , "Grizzy"));
            imageList.add(new Image(R.drawable.panda_doll , "Panda"));
            imageList.add(new Image(R.drawable.ice_bear_doll , "Ice Bear"));
        }

        if(dollList.isEmpty()) {
            dollList.add(new Doll(1 , "1" , "The 1" , R.drawable.grizzly_doll));
            dollList.add(new Doll(2 , "2" , "The 2" , R.drawable.gradient_background));
            dollList.add(new Doll(3 , "3" , "The 3" , R.drawable.gradient_background));
            dollList.add(new Doll(4 , "4" , "The 4" , R.drawable.gradient_background));
            dollList.add(new Doll(5 , "5" , "The 5" , R.drawable.gradient_background));
            dollList.add(new Doll(6 , "6" , "The 6" , R.drawable.gradient_background));
            dollList.add(new Doll(7 , "7" , "The 7" , R.drawable.gradient_background));
            dollList.add(new Doll(8 , "8" , "The 8" , R.drawable.gradient_background));
            dollList.add(new Doll(9 , "9" , "The 9" , R.drawable.gradient_background));
            dollList.add(new Doll(10 , "10" , "The 10" , R.drawable.gradient_background));
            dollList.add(new Doll(11 , "11" , "The 11" , R.drawable.gradient_background));
            dollList.add(new Doll(12 , "12" , "The 12" , R.drawable.gradient_background));
            dollList.add(new Doll(13 , "13" , "The 13" , R.drawable.gradient_background));
            dollList.add(new Doll(14 , "14" , "The 14" , R.drawable.gradient_background));
            dollList.add(new Doll(15 , "15" , "The 15" , R.drawable.gradient_background));
            dollList.add(new Doll(16 , "16" , "The 16" , R.drawable.gradient_background));
            dollList.add(new Doll(17 , "17" , "The 17" , R.drawable.gradient_background));
            dollList.add(new Doll(18 , "18" , "The 18" , R.drawable.gradient_background));
            dollList.add(new Doll(19 , "19" , "The 19" , R.drawable.gradient_background));
            dollList.add(new Doll(20 , "20" , "The 20" , R.drawable.gradient_background));
            dollList.add(new Doll(21 , "21" , "The 21" , R.drawable.gradient_background));
            dollList.add(new Doll(22 , "22" , "The 22" , R.drawable.gradient_background));
            dollList.add(new Doll(23 , "23" , "The 23" , R.drawable.gradient_background));
            dollList.add(new Doll(24 , "24" , "The 24" , R.drawable.gradient_background));
            dollList.add(new Doll(25 , "25" , "The 25" , R.drawable.gradient_background));
            dollList.add(new Doll(26 , "26" , "The 26" , R.drawable.gradient_background));
            dollList.add(new Doll(27 , "27" , "The 27" , R.drawable.gradient_background));
        }
        if(dollList.isEmpty())
            Toast.makeText(this , "Sorry, there is no doll available!" , Toast.LENGTH_SHORT).show();

        dAdapter = new DollAdapter(this,dollList);
        lvDoll.setAdapter(dAdapter);

    }

}
