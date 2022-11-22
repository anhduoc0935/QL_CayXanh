package com.example.ql_cayxanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ql_cayxanh.adapter.CayXanhAdapter;
import com.example.ql_cayxanh.model.CayXanh;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ql_cayxanh extends AppCompatActivity {
    private ListView lv_cay;
    private ArrayList<CayXanh> cayXanhArrayList;
    private CayXanhAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_cayxanh);

         lv_cay =findViewById(R.id.ql_cay);

        cayXanhArrayList =new ArrayList<>();
        GetData();

        /*cayXanhArrayList.add(new CayXanh("cay tung","bang","song lau","xanh"));
        cayXanhArrayList.add(new CayXanh("cay tung","bang","song lau","xanh"));
        cayXanhArrayList.add(new CayXanh("cay tung","bang","song lau","xanh"));*/

         adapter =new CayXanhAdapter(this, R.layout.listview_items,cayXanhArrayList);

        lv_cay.setAdapter(adapter);

    }
    public void ArrayCayXanh(){
        lv_cay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ql_cayxanh.this, CayInformation.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });


}
    private void GetData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("DbQlCayXanh");
myref.addValueEventListener(new ValueEventListener() {

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
adapter.clear();
for (DataSnapshot data : dataSnapshot.getChildren()){
    CayXanh cayXanh =data.getValue(CayXanh.class);

        cayXanh.setId(data.getKey());
        adapter.add(cayXanh);
    Log.d("MYTAG","onDataChange: " + cayXanh.getTenKhoaHoc());
}
        Toast.makeText(getApplicationContext(),"Load data thanh cong",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(getApplicationContext(),"Load data loi"+ databaseError.toString(),Toast.LENGTH_LONG).show();
        Log.d("MYTAG","onCancelled: "+ databaseError.toString());
    }
});
    }
}