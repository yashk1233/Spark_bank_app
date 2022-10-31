package com.example.listviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbhandler  extends SQLiteOpenHelper {
    public Dbhandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = " create table customer (cid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cname TEXT, balance  INTEGER,address Text, age INTERGER)";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop = String.valueOf("drop table if exists");
        db.execSQL(drop,new String[]{"customer"});
        onCreate(db);

    }

    public void addCustomer(Customer c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("cname",c.getCname());
        values.put("balance",c.getBalance());
        values.put("address",c.getAddress());
        values.put("age",c.getAge());
        long k = db.insert("customer",null,values);
        Log.d("mytag",Long.toString(k));
        db.close();

    }

    public void getCustomer(int cid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("customer",new String[]{"cid","cname","balance,address,age"},"cid=?",new String[]{String.valueOf(cid)},null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            Log.d("mytag",cursor.getString(1));

            Log.d("mytag",cursor.getString(2));
        }
        else{
            Log.d("mytag","some error occured");
        }
    }


    public List<Customer> getAllCustomer(){
        List<Customer> customers = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from customer";
        Cursor cursor = db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do{
                Customer c = new Customer();
                c.setCid(Integer.parseInt(cursor.getString(0)));
                c.setCname(cursor.getString(1));
                c.setBalance(Integer.parseInt(cursor.getString(2)));
                customers.add(c);

            }while(cursor.moveToNext());
            Log.d("mytag","data found");
            Log.d("mytag2",""+customers.get(2));
        }
        else {
            Log.d("mytag","no daata");
        }
        return customers;
    }



}
