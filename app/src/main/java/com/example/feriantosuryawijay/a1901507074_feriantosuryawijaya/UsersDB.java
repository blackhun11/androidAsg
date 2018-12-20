package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_USER_EMAIL;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_USER_NAME;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_USER_PASSWORD;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.TABLE_USERS;

public class UsersDB {

    private DBHelper dbHelper;

    public UsersDB ( Context context ) {
        dbHelper = new DBHelper(context);
    }

    public void store ( String name , String email , String password ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_USER_NAME , name);
        contentValues.put(FIELD_USER_EMAIL , email);
        contentValues.put(FIELD_USER_PASSWORD , password);
        db.insert(TABLE_USERS , null , contentValues);
        db.close();
    }

    public boolean checkUserExists ( String email ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = String.format("%s = ?" , FIELD_USER_EMAIL);
        String[] selectionArgs = new String[]{email};
        Cursor cursor = db.query(TABLE_USERS , null , selection , selectionArgs , null , null , null);
        cursor.moveToFirst();
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        return exists;
    }

    public String authenticateUser ( String email , String password ) {
        String name = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = String.format("%s = ? AND %s = ?" , FIELD_USER_EMAIL , FIELD_USER_PASSWORD);
        String[] selectionArgs = new String[]{email , password};
        Cursor cursor = db.query(TABLE_USERS , null , selection , selectionArgs , null , null , null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) name = cursor.getString(cursor.getColumnIndex(FIELD_USER_NAME));
        cursor.close();
        return name;
    }
}
