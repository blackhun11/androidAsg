package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DollAdapter extends ArrayAdapter<Doll> {

//    private ArrayList<Doll> dollList = new ArrayList<>();
//    private Context context;
//    TextView name, description;
//    ImageView image;
//    Button btnEdit, btnView;
//    Doll currentDoll;
////
//    public DollAdapter(ArrayList<Doll> list, Context context){
//        this.dollList = list;
//        this.context = context;
//    }

//    @Override
//    public int getCount() {
//        return dollList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return dollList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return dollList.get(position).getId();
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        View view = convertView;
//        if(view == null){
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.list_view_doll, null);
//        }
//
//        currentDoll = dollList.get(position);
////
//        image = (ImageView)view.findViewById(R.id.ivDoll);
//        image.setImageResource(currentDoll.getImage());
//        name = (TextView) view.findViewById(R.id.tvDollName);
//        name.setText(currentDoll.getName());
//
//        description = (TextView) view.findViewById(R.id.tvDollDesc);
//        description.setText(currentDoll.getDescription());
//
//        btnEdit = (Button) view.findViewById(R.id.btnEdit);
//
//        btnView = (Button) view.findViewById(R.id.btnView);
//
//        btnEdit.setOnClickListener( new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context,ViewDollDetailActivity.class);
//                intent.putExtra("id", dollList.get(position).getId());
//                intent.putExtra("name", dollList.get(position).getName());
//                intent.putExtra("description", dollList.get(position).getDescription());
//                intent.putExtra("image", dollList.get(position).getImage());
//
//                context.startActivity(intent);
//
//            }
//        });
//
//        return view;
//    }

    private Context dContext;
    Button btnEdit, btnView;
    TextView name, description;
    ImageView image;
    Doll currentDoll;

    private ArrayList<Doll> dollList = new ArrayList<>();
    public DollAdapter(@NonNull Context context, ArrayList<Doll> list) {
        super(context, 0 , (ArrayList<Doll>) list);
        dContext = context;
        dollList = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(dContext).inflate(R.layout.list_view_doll,parent,false);

        currentDoll = dollList.get(position);

        image = (ImageView)listItem.findViewById(R.id.ivDoll);
        image.setImageResource(currentDoll.getImage());

        name = (TextView) listItem.findViewById(R.id.tvDollName);
        name.setText(currentDoll.getName());

        description = (TextView) listItem.findViewById(R.id.tvDollDesc);
        description.setText(currentDoll.getDescription());

        btnEdit = (Button) listItem.findViewById(R.id.btnEdit);
        btnView = (Button) listItem.findViewById(R.id.btnView);

        btnEdit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dContext,ModifyDollActivity.class);
                intent.putExtra("id", dollList.get(position).getId());
                intent.putExtra("name", dollList.get(position).getName());
                intent.putExtra("description", dollList.get(position).getDescription());
                intent.putExtra("image", dollList.get(position).getImage());

                dContext.startActivity(intent);
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dContext,ViewDollDetailActivity.class);
                intent.putExtra("id", dollList.get(position).getId());
                intent.putExtra("name", dollList.get(position).getName());
                intent.putExtra("description", dollList.get(position).getDescription());
                intent.putExtra("image", dollList.get(position).getImage());

                dContext.startActivity(intent);
            }
        });

        return listItem;
    }

}
