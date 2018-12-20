package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewDollActivity extends BaseActivity {

    private ListView lvDoll;
    List<Doll> dollList = new ArrayList<>();
    DollsDB dollsDB;

    private void populate () {

        //append spinner
//        if (imageList.isEmpty()) {
//            imageList.add(new Image(0 , "Choose doll image"));
//            imageList.add(new Image(R.drawable.grizzly_doll , "Grizzy"));
//            imageList.add(new Image(R.drawable.panda_doll , "Panda"));
//            imageList.add(new Image(R.drawable.ice_bear_doll , "Ice Bear"));
//        }

        //append dummy doll data
//        if (dollList.isEmpty()) {
//            dollList.add(new Doll(1 , "Grizzly" , "We Bare Bear Grizzly" , R.drawable.grizzly_doll));
//            dollList.add(new Doll(2 , "Panda" , "We Bare Bear Panda" , R.drawable.panda_doll));
//            dollList.add(new Doll(3 , "Ice Bear" , "We Bare Bear Ice Bear" , R.drawable.ice_bear_doll));
//        }
        dollList = dollsDB.show();

        //if no doll
        if (dollList.isEmpty())
            Toast.makeText(this , "Sorry, there is no doll available!" , Toast.LENGTH_SHORT).show();

        DollAdapter dAdapter = new DollAdapter(this , dollList);
        lvDoll.setAdapter(dAdapter);
    }

    @Override
    protected void onResume () {
        super.onResume();
        populate();
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doll);

        dollsDB = new DollsDB(this);
        lvDoll = findViewById(R.id.lvDoll);

        populate();
    }

}
