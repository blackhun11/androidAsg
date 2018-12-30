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

    public void store ( User user ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_USER_NAME , user.getName());
        contentValues.put(FIELD_USER_EMAIL , user.getEmail());
        contentValues.put(FIELD_USER_PASSWORD , user.getPassword());
        db.insert(TABLE_USERS , null , contentValues);
        db.close();
    }

    public boolean checkUserExists ( User user ) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = String.format("%s = ?" , FIELD_USER_EMAIL);
        String[] selectionArgs = new String[]{user.getEmail()};
        Cursor cursor = db.query(TABLE_USERS , null , selection , selectionArgs , null , null , null);
        cursor.moveToFirst();
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        return exists;
    }

    public String authenticateUser ( User user ) {
        String name = "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = String.format("%s = ? AND %s = ?" , FIELD_USER_EMAIL , FIELD_USER_PASSWORD);
        String[] selectionArgs = new String[]{user.getEmail() , user.getPassword()};
        Cursor cursor = db.query(TABLE_USERS , null , selection , selectionArgs , null , null , null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) name = cursor.getString(cursor.getColumnIndex(FIELD_USER_NAME));
        cursor.close();
        return name;
    }
}
