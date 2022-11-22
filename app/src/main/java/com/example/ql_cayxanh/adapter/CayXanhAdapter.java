package com.example.ql_cayxanh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ql_cayxanh.model.CayXanh;
import com.example.ql_cayxanh.R;
import com.example.ql_cayxanh.model.CayXanh;
import com.example.ql_cayxanh.them_CayXanh;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class CayXanhAdapter extends ArrayAdapter<CayXanh> {

    private Activity activity;
    private int resource;
    @NonNull
    private List<CayXanh> objects;

    public CayXanhAdapter(@NonNull Activity activity, int resource, @NonNull List<CayXanh> objects) {
        super(activity, resource, objects);
        this.activity=activity;
        this.objects=objects;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =this.activity.getLayoutInflater();
        View view=inflater.inflate(this.resource, null);

        TextView txtTen =view.findViewById(R.id.txtten);
        TextView txtTenthuong = view.findViewById(R.id.txttenthuong);

        CayXanh cayXanh =this.objects.get(position);
        txtTen.setText(cayXanh.getTenKhoaHoc());
        txtTenthuong.setText(cayXanh.getTenThuongGoi());

        ImageView btnMenu=view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId()==R.id.item_themCX){
                            Intent intent = new Intent(activity, them_CayXanh.class);
                            activity.startActivity(intent);
                        }

                        else if (menuItem.getItemId()==R.id.item_XoaCX){
                            FirebaseDatabase database =FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("DbQlCayXanh");
                            myRef.child(cayXanh.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(activity,"Them thanh cong!",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.show();
            }
        });
        return view;
    }
}
