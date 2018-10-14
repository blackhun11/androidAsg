package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.ViewDollActivity.dollList;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.ViewDollActivity.imageList;


public class ModifyDollActivity extends BaseActivity implements View.OnClickListener {

    EditText etDollName, etDollDesc;
    Spinner spinnerImage;
    Button btnSaveDoll, btnDeleteDoll;
    private ArrayAdapter<Image> iAdapter;
    int id = 0, image = 0;
    String name, description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_modify_doll);

        Intent intent = getIntent();
        id = intent.getIntExtra("id" ,0);
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        image = intent.getIntExtra("image",0);

        etDollName = (EditText) findViewById(R.id.etDollName);
        etDollDesc = (EditText) findViewById(R.id.etDollDesc);
        spinnerImage = (Spinner) findViewById(R.id.spinnerImage);
        btnSaveDoll = (Button) findViewById(R.id.btnSaveDoll);
        btnDeleteDoll = (Button) findViewById(R.id.btnDeleteDoll);

        iAdapter = new ArrayAdapter<Image>(this, android.R.layout.simple_spinner_item, imageList);

        iAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerImage.setAdapter(iAdapter);
        if (id == 0){
            this.btnDeleteDoll.setEnabled(false);
            btnSaveDoll.setText("Save");
        }else{
            int indexPosition = 0;
            for (Image img : imageList) {
                if (image == img.getId()) {
                    indexPosition = iAdapter.getPosition(img);
                }
            }

            btnDeleteDoll.setOnClickListener(this);
            btnSaveDoll.setText("Modify");
            etDollName.setText(name);
            etDollDesc.setText(description);

            spinnerImage.setSelection(indexPosition);
        }
        btnSaveDoll.setOnClickListener(this);
    }

    @Override
    public void onClick ( View v ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        String imageInputNew = "", descriptionNew = "", nameNew = "";
        int imageID = 0;
        View view = findViewById(R.id.modifyDoll);

        final String nameNew = etDollName.getText().toString();
        final String descriptionNew = etDollDesc.getText().toString();
        final String imageInputNew = spinnerImage.getSelectedItem().toString();

        if (v == btnSaveDoll){
            boolean isExist = false;

            for (Doll doll : dollList) {
                if (doll.getName().equals(nameNew)) {
                    isExist = true;
                    break;
                }
            }

            if (nameNew.isEmpty()){
                snack(view, "Name must be filled!");
            }else if(isExist){
                snack(view, "Doll name already exists, please use another name!");
            }else if(descriptionNew.isEmpty()){
                snack(view, "Description must be filled!");
            }else if(imageInputNew.equals(-1)){
                snack(view, "Doll image must be choosen!");
            }else{
                for (Image image : imageList) {
                    if (image.getName().equals(imageInputNew)) {
                        imageID = image.getId();
                        break;
                    }
                }

                final int imageIDNew = imageID;

                if (id != 0){

                    builder.setTitle("Modify Confirmation");
                    builder.setMessage("Are you sure want to modify?");
                    builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            for(int i = 0; i < dollList.size(); i++){
                                if(id == dollList.get(i).getId()){
                                    dollList.set(i,new Doll(id, nameNew, descriptionNew, imageIDNew));
                                    break;
                                }
                            }
                            Toast.makeText(ModifyDollActivity.this,"Modified",Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();

                }else {
                    int id = (dollList.size() == 0) ? 1: dollList.get(dollList.size() - 1).getId() + 1;
                    final int idNew = id;
                    builder.setTitle("Save Confirmation");
                    builder.setMessage("Are you sure want to add new doll?");
                    builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            dollList.add(new Doll(idNew , nameNew , descriptionNew , imageIDNew));
                            resetET(new EditText[]{etDollName, etDollDesc});
                            Toast.makeText(ModifyDollActivity.this,"Success to add "+ nameNew,Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();

                }
            }
        }else if (v == btnDeleteDoll){
            builder.setTitle("Delete Confirmation");
            builder.setMessage("Are you sure want to delete?");
            builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    for(int i = 0; i < dollList.size(); i++){
                        if(id == dollList.get(i).getId()){
                            dollList.remove(i);
                            break;
                        }
                    }
                    startActivity(new Intent(ModifyDollActivity.this,ViewDollActivity.class));
                    finish();
                    Toast.makeText(ModifyDollActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }
    }

}
