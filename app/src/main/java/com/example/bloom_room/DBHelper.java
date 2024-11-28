package com.example.bloom_room;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "userdata.db";
    public DBHelper (Context context)
    {
        super(context,"userdata.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
//create table - set colum username and password
        MyDB.execSQL("create Table users(username TEXT , Email TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table flowers(flowername TEXT , price TEXT , description TEXT,category TEXT,id TEXT primary key )");
        MyDB.execSQL("create Table admin(username TEXT , Email TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table category(name TEXT , id TEXT primary key)");


        // default admin password
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", "admin");
        contentValues.put("Email", "admin@default.com");
        contentValues.put("password", "admin123");
        MyDB.insert("admin", null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists flowers");
        MyDB.execSQL("drop Table if exists admin");
        MyDB.execSQL("drop Table if exists category");


    }

//Admin unsename gen
    private void insertDefaultAdminData(SQLiteDatabase MyDB) {
        // Check if default admin already exists
        Cursor cursor = MyDB.rawQuery("SELECT * FROM admin WHERE Email = 'admin@default.com'", null);
        if (cursor.getCount() == 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", "admin");
            contentValues.put("Email", "admin@default.com");
            contentValues.put("password", "admin123");
            MyDB.insert("admin", null, contentValues);
        }
        cursor.close();
    }

//categorydatabase

    public Boolean insertCategoryrData(String categoryname , String categoryid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum
        contentValues.put("name", categoryname);
        contentValues.put("id", categoryid);

        long result = MyDB.insert("category", null, contentValues);
        if (result != -1) return true;
        else return false;

    }

    public Cursor getcategorydata() {
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from category",null);
        return cursor;
    }


    public Boolean updatecategorydata(String name,String id ){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum
        contentValues.put("name", name);
        contentValues.put("id", id);

        Cursor cursor = DB.rawQuery("select * from category where id = ?", new String[]{id});
        if (cursor.getCount() > 0 ) {
            long result = DB.update("category",  contentValues,"id=?", new String[]{id});
            if (result == 1) {return true;}

            else {
                return false;
            }
        }else {
            return false;
        }
    }


    public Boolean deletecategorydata (String id){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from category where id = ?", new String[]{id});
        if (cursor.getCount() > 0 ) {
            long result = DB.delete("category","id=?", new String[]{id});
            if (result == 1) {return true;}

            else {
                return false;
            }
        }else {
            return false;
        }



    }


    //Flowerdatabase
    public Boolean insertflowerData(String flowername , String price ,String description, String category ,String id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum

        contentValues.put("flowername", flowername);
        contentValues.put("price", price);
        contentValues.put("description", description);
        contentValues.put("category", category);
        contentValues.put("id", id);


        long result = MyDB.insert("flowers", null, contentValues);
        if (result != -1) return true;
        else return false;

    }

    public Cursor getflowerdata() {
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from flowers",null);
        return cursor;
    }

    public Boolean updateflowerdata(String flowername , String price ,String description, String category,String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum
        contentValues.put("flowername", flowername);
        contentValues.put("price", price);
        contentValues.put("description", description);
        contentValues.put("id", id);
        contentValues.put("category", category);


        Cursor cursor = DB.rawQuery("select * from flowers where id = ?", new String[]{id});
        if (cursor.getCount() > 0 ) {
            long result = DB.update("flowers",  contentValues,"id=?", new String[]{id});
            if (result == 1) {return true;}

            else {
                return false;
            }
        }else {
            return false;
        }
    }


    public Boolean deleteflowerdata (String id){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from flowers where id = ?", new String[]{id});
        if (cursor.getCount() > 0 ) {
            long result = DB.delete("flowers","id=?", new String[]{id});
            if (result == 1) {return true;}

            else {
                return false;
            }
        }else {
            return false;
        }



    }










//userdatabase
    public Boolean insertData(String user , String email ,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum
        contentValues.put("username", user);
        contentValues.put("Email", email);
        contentValues.put("password", password);


        long result = MyDB.insert("users", null, contentValues);
        if (result != -1) return true;
        else return false;

    }


    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE username=?", new String[] { username });

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkemail(String Email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE Email=?", new String[] { Email });

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?",
                new String[] {username,password});
        if (cursor.getCount() > 0 )
            return true;
        else
            return false;
    }

    public Boolean resetPassword(String email, String newUsername, String newPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("email", email);
        contentValues.put("username", newUsername);
        contentValues.put("password", newPassword);


        int rowsAffected = MyDB.update("users", contentValues, "Email=?", new String[]{email});
        return rowsAffected > 0;
    }

//Admin database



    public Boolean insertAdminData(String user , String email ,String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //input the values into the colum
        contentValues.put("username", user);
        contentValues.put("Email", email);
        contentValues.put("password", password);


        long result = MyDB.insert("admin", null, contentValues);
        if (result != -1) return true;
        else return false;

    }

    public Boolean checkAdminusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM admin WHERE username=?", new String[] { username });

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkAdminemail(String Email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM admin WHERE Email=?", new String[] { Email });

        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkAdminusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from admin where username = ? and password = ?",
                new String[] {username,password});
        if (cursor.getCount() > 0 )
            return true;
        else
            return false;
    }

    public Boolean resetAdminPassword(String email, String newUsername, String newPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("email", email);
        contentValues.put("username", newUsername);
        contentValues.put("password", newPassword);


        int rowsAffected = MyDB.update("admin", contentValues, "Email=?", new String[]{email});
        return rowsAffected > 0;
    }


}
