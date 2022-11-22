package com.example.ql_cayxanh;

import static com.example.ql_cayxanh.R.id.edtTenKH;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_cayxanh.model.CayXanh;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class them_CayXanh extends AppCompatActivity {
private EditText edtTenKH,edtTenTG,edtDactinh,edtMaula;
private Button btnThem, btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cay_xanh);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhoaHoc=edtTenKH.getText().toString();
                String tenThuongGoi=edtTenTG.getText().toString();
                String dacTinh=edtDactinh.getText().toString();
                String mauLa=edtMaula.getText().toString();

                CayXanh cayXanh =new CayXanh(tenKhoaHoc,tenThuongGoi,dacTinh,mauLa);

                FirebaseDatabase database =FirebaseDatabase.getInstance();
                DatabaseReference myRef= database.getReference("DbQlCayXanh");
                String id = myRef.push().getKey();
                myRef.child(id).setValue(cayXanh).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Them thanh cong!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Them that bai!"+e.toString(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void addControls() {

        edtTenKH =findViewById(R.id.edtTenKH);
        edtTenTG =findViewById(R.id.edtTenTG);
        edtDactinh =findViewById(R.id.edtDactinh);
        edtMaula =findViewById(R.id.edtMaula);

        btnThem =findViewById(R.id.btnthem);
        btnReturn =findViewById(R.id.btnReturn);

    }
}