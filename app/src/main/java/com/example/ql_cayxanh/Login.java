package com.example.ql_cayxanh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText password = (EditText) findViewById(R.id.pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button login = (Button) findViewById(R.id.login);
        final Button signup = (Button) findViewById(R.id.SignUp);





        final SQLServer sqlSever = new SQLServer(this);
        ArrayList<User> list = new ArrayList<>();
        User s = new User("tranduoc", "duoctran0935@gmail.com", "120601");
        sqlSever.AddUser(s);

        password.setInputType(InputType.TYPE_CLASS_TEXT |//ẩn Text để làm mật khẩu
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUp();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                if(name.equals("") || pass.equals("")){
                    Toast.makeText( Login.this, "Vui Lòng Điền Đủ Thông tin!!!", Toast.LENGTH_SHORT).show();
                }else{
                    User s = sqlSever.getUser(name);
                    if(s != null){
                        if(s.getPassword().equals(pass)){

                            Toast.makeText( Login.this, "Đăng nhập thành công ^.^", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            username.setText("");
                            Login(s);

                        }else {
                            password.setText("");
                            username.setText("");
                            Toast.makeText( Login.this, "Tài khoản hoặc mật khẩu không chính xác!!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        password.setText("");
                        username.setText("");
                        Toast.makeText( Login.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void OpenSignUp(){
        Intent intent = new Intent( Login.this, SignUp.class);
        startActivity(intent);
    }

    public void Login(User s){
        Intent intent = new Intent( Login.this, ql_cayxanh.class); //LayOutAndLisView.class);
        intent.putExtra(Intent.EXTRA_USER, s.getAccount());
        startActivity(intent);
        finish();

    }
}