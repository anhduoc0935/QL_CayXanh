package com.example.ql_cayxanh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLServer extends SQLiteOpenHelper {
    private static final String DatabaseName = "library";

    private static final String Table_Name1 = "users";
    private static final String UserID = "userid";
    private static final String Account = "account";
    private static final String Email = "email";
    private static final String Password = "password";
    private static int version = 1;

    private SQLiteDatabase db;
    private Context context;
    private ContentValues values;

    public SQLServer(Context context) {
        super(context, DatabaseName, null, version);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table_user = " CREATE TABLE " + Table_Name1 + " ( " +
                UserID + " integer primary key, " +
                Account + " TEXT, " +
                Email + " TEXT, " +
                Password + " TEXT)" ;
        db.execSQL(Create_Table_user);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name1);
        onCreate(db);
    }

    public void AddUser(User user) {
        db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(Account, user.getAccount());

        values.put(Email, user.getGmail());
        values.put(Password, user.getPassword());

        db.insert(Table_Name1, null, values);
    }

    public ArrayList<User> getArrayUser(){
        ArrayList<User> list = new ArrayList<>();
        String selectUser = "select * from " + Table_Name1;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectUser, null);
        if(cursor.moveToFirst()){
            do{
                User s = new User();
                s.setUserID(cursor.getInt(0));
                s.setAccount(cursor.getString(1));

                s.setGmail(cursor.getString(2));
                s.setPassword(cursor.getString(3));

                list.add(s);
            }while (cursor.moveToNext());
        }else{
            list = null;
        }
        return list;
    }
    public User getUser(String account){
        User s = new User();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(Table_Name1, new String[]{UserID,Account, Email, Password},
                "Account=?", new String[]{String.valueOf(account)}, null, null, null,null);

        if(cursor.moveToFirst()){
            s.setUserID(cursor.getInt(0));
            s.setAccount(cursor.getString(1));

            s.setGmail(cursor.getString(2));
            s.setPassword(cursor.getString(3));

        }else{
            s = null;
        }
        return s;
    }


    public boolean checkUser(String email){
        String[] columns = {
                UserID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = Email + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(Table_Name1,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }
}
