package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DollAdapter extends BaseAdapter {

    private List<Doll> dollList;
    private Context dContext;
    Button btnEdit, btnView;
    TextView name, description;
    ImageView image;

    /* Doll Adapter constructor */
    DollAdapter ( Context context , List<Doll> list ) {
        dContext = context;
        dollList = list;
    }

    /* override getCount, getItem, getItemId, getView */
    @Override
    public int getCount () {
        return dollList.size();
    }

    @Override
    public Object getItem ( int position ) {
        return dollList.get(position);
    }

    @Override
    public long getItemId ( int position ) {
        return position;
    }

    @NonNull
    @Override
    public View getView ( final int position , @Nullable final View convertView , @NonNull ViewGroup parent ) {

        View listItem = convertView;
        if (listItem == null)
            /* inflate list_view_doll.xml */
            listItem = LayoutInflater.from(dContext).inflate(R.layout.list_view_doll , parent , false);

        /* initialize current doll */
        Doll currentDoll = dollList.get(position);

        /* set value image, name, desc and setOnclickListener per list */
        image = listItem.findViewById(R.id.ivDoll);
        image.setImageResource(currentDoll.getImage());

        name = listItem.findViewById(R.id.tvDollName);
        name.setText(currentDoll.getName());

        description = listItem.findViewById(R.id.tvDollDesc);
        description.setText(currentDoll.getDescription());

        btnEdit = listItem.findViewById(R.id.btnEdit);
        btnView = listItem.findViewById(R.id.btnView);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                //parsing data using intent
                Intent intent = new Intent(dContext , ModifyDollActivity.class);
                intent.putExtra("id" , dollList.get(position).getId());
                intent.putExtra("name" , dollList.get(position).getName());
                intent.putExtra("description" , dollList.get(position).getDescription());
                intent.putExtra("image" , dollList.get(position).getImage());

                dContext.startActivity(intent);
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                //parsing data using intent
                Intent intent = new Intent(dContext , ViewDollDetailActivity.class);
                intent.putExtra("id" , dollList.get(position).getId());
                intent.putExtra("name" , dollList.get(position).getName());
                intent.putExtra("description" , dollList.get(position).getDescription());
                intent.putExtra("image" , dollList.get(position).getImage());

                dContext.startActivity(intent);
            }
        });

        return listItem;
    }

}
