package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_DOLL_DESCRIPTION;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_DOLL_ID;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_DOLL_IMAGE_ID;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_DOLL_NAME;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.FIELD_IMAGE_ID;
import static com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya.DBHelper.TABLE_DOLLS;

public class DollsDB {

    private DBHelper dbHelper;

    public DollsDB ( Context context ) {
        dbHelper = new DBHelper(context);
    }

    public boolean checkDollExist ( String name ) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = String.format("%s = ?" , FIELD_DOLL_NAME);
        String[] selectionArgs = new String[]{name};
        Cursor cursor = db.query(TABLE_DOLLS , null , selection , selectionArgs , null , null , null);
        cursor.moveToFirst();
        boolean exists = cursor.getCount() != 0;
        cursor.close();
        return exists;
    }

    public void update ( Doll doll ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_DOLL_IMAGE_ID , doll.getImage());
        cv.put(FIELD_DOLL_NAME , doll.getName());
        cv.put(FIELD_DOLL_DESCRIPTION , doll.getDescription());
        String whereClause = String.format("%s = ?" , FIELD_DOLL_ID);
        String[] whereArgs = new String[]{"" + doll.getId()};
        db.update(TABLE_DOLLS , cv , whereClause , whereArgs);
        db.close();
    }

    public void store ( Doll doll ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_DOLL_IMAGE_ID , doll.getImage());
        cv.put(FIELD_DOLL_NAME , doll.getName());
        cv.put(FIELD_DOLL_DESCRIPTION , doll.getDescription());
        db.insert(TABLE_DOLLS , null , cv);
        db.close();
    }

    public void delete ( Doll doll ) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClause = String.format("%s = ?" , FIELD_DOLL_ID);
        String[] whereArgs = new String[]{"" + doll.getId()};
        db.delete(TABLE_DOLLS , whereClause , whereArgs);
        db.close();
    }

    public List<Doll> show () {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DOLLS , null , null , null , null , null , null);
        List<Doll> dollList = new ArrayList<>();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(FIELD_DOLL_ID));
            int image_id = cursor.getInt(cursor.getColumnIndex(FIELD_DOLL_IMAGE_ID));
            String name = cursor.getString(cursor.getColumnIndex(FIELD_DOLL_NAME));
            String description = cursor.getString(cursor.getColumnIndex(FIELD_DOLL_DESCRIPTION));

            Doll doll = new Doll(id , image_id , name , description);
            dollList.add(doll);
        }
        cursor.close();
        return dollList;
    }
}
