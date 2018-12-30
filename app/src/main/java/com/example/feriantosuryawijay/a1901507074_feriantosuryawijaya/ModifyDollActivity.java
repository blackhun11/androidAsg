package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;


public class ModifyDollActivity extends BaseActivity implements View.OnClickListener {

    EditText etDollName, etDollDesc;
    Spinner spinnerImage;
    Button btnSaveDoll, btnDeleteDoll;
    int id = 0, image = 0;
    String name, description = "";
    List<Image> imageList;
    ImageDB imageDB;
    DollsDB dollsDB;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_modify_doll);

        imageDB = new ImageDB(this);
        dollsDB = new DollsDB(this);
        boolean imageExist = imageDB.checkImageExists();

        if (!imageExist) populate();

        imageList = imageDB.create();
        imageList.add(0 , new Image(0 , "Choose Doll Image"));

        /* get intent data */
        Intent intent = getIntent();
        id = intent.getIntExtra("id" , 0);
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        image = intent.getIntExtra("image" , 0);

        etDollName = findViewById(R.id.etDollName);
        etDollDesc = findViewById(R.id.etDollDesc);
        spinnerImage = findViewById(R.id.spinnerImage);
        btnSaveDoll = findViewById(R.id.btnSaveDoll);
        btnDeleteDoll = findViewById(R.id.btnDeleteDoll);

        /* append spinner option from imageList */
        ArrayAdapter<Image> iAdapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , imageList);
        iAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerImage.setAdapter(iAdapter);

        /* determine add or modify */
        if (id == 0) {
            /* add */
            this.btnDeleteDoll.setEnabled(false);
            btnSaveDoll.setText(R.string.save);
        } else {
            /* modify */
            int indexPosition = 0;
            for (Image img : imageList) {
                if (image == img.getId()) {
                    indexPosition = iAdapter.getPosition(img);
                }
            }

            btnDeleteDoll.setOnClickListener(this);
            btnSaveDoll.setText(R.string.modify);
            etDollName.setText(name);
            etDollDesc.setText(description);
            spinnerImage.setSelection(indexPosition);
        }

        btnSaveDoll.setOnClickListener(this);
    }

    private void populate () {
        imageDB.store(new Image(R.drawable.grizzly_doll, "Grizzly"));
        imageDB.store(new Image(R.drawable.panda_doll, "Panda"));
        imageDB.store(new Image(R.drawable.ice_bear_doll, "Ice Bear"));
    }

    @Override
    public void onClick ( View v ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        int imageID = 0;
        View view = findViewById(R.id.modifyDoll);

        final String nameNew = etDollName.getText().toString();
        final String descriptionNew = etDollDesc.getText().toString();
        final String imageInputNew = spinnerImage.getSelectedItem().toString();
        final int spinnerIndex = spinnerImage.getSelectedItemPosition();
        if (v == btnSaveDoll) {


            boolean isExist = dollsDB.checkDollExist(new Doll(nameNew));

//            for (Doll doll : dollList) {
//                if (doll.getName().equals(nameNew)) {
//                    isExist = true;
//                    break;
//                }
//            }
            /* validation based on Assignment Case for insert & update*/
            if (nameNew.isEmpty()) {
                snack(view , "Name must be filled!");
            } else if (isExist) {
                snack(view , "Doll name already exists, please use another name!");
            } else if (descriptionNew.isEmpty()) {
                snack(view , "Description must be filled!");
            } else if (spinnerIndex == 0) {
                snack(view , "Doll image must be choosen!");
            } else {

                for (Image image : imageList) {
                    if (image.getName().equals(imageInputNew)) {
                        imageID = image.getId();
                        break;
                    }
                }

                final int imageIDNew = imageID;

                final Doll doll = new Doll(id , imageIDNew , nameNew , descriptionNew);

                if (id != 0) {
                    /* modify */
                    builder.setTitle("Modify Confirmation");
                    builder.setMessage("Are you sure want to modify?");
                    builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
//                            dollList.set(position , new Doll(id , nameNew , descriptionNew , imageIDNew));
                            dollsDB.update(doll);
                            Toast.makeText(ModifyDollActivity.this , "Modified" , Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("No" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();

                } else {
                    /* add */
//                    final int id = (dollList.size() == 0) ? 1 : dollList.get(dollList.size() - 1).getId() + 1;
                    builder.setTitle("Save Confirmation");
                    builder.setMessage("Are you sure want to add new doll?");
                    builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick ( DialogInterface dialog , int which ) {
//                            dollList.add(new Doll(id , nameNew , descriptionNew , imageIDNew));
                            dollsDB.store(doll);
                            resetET(new EditText[]{etDollName , etDollDesc});
                            Toast.makeText(ModifyDollActivity.this , "Success to add " + nameNew , Toast.LENGTH_SHORT).show();
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
        } else if (v == btnDeleteDoll) {
            /* delete */
            builder.setTitle("Delete Confirmation");
            builder.setMessage("Are you sure want to delete?");
            builder.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
                @Override
                public void onClick ( DialogInterface dialog , int which ) {
                    Doll doll = new Doll();
                    doll.setId(id);
                    dollsDB.delete(doll);
//                    dollList.remove(id);
                    startActivity(new Intent(ModifyDollActivity.this , ViewDollActivity.class));
                    finish();
                    Toast.makeText(ModifyDollActivity.this , "Deleted" , Toast.LENGTH_SHORT).show();
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
