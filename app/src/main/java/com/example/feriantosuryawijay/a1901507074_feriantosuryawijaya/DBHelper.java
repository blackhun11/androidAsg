package com.example.feriantosuryawijay.a1901507074_feriantosuryawijaya;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "BlueDoll";
    public static final int DB_VERSION = 1;

    public static final String TABLE_USERS = "Users";
    public static final String FIELD_USER_ID = "id";
    public static final String FIELD_USER_NAME = "name";
    public static final String FIELD_USER_EMAIL = "email";
    public static final String FIELD_USER_PASSWORD = "password";

    public static final String TABLE_DOLLS = "Dolls";
    public static final String FIELD_DOLL_ID = "id";
    public static final String FIELD_DOLL_IMAGE_ID = "image_id";
    public static final String FIELD_DOLL_NAME = "name";
    public static final String FIELD_DOLL_DESCRIPTION = "description";

    public static final String TABLE_IMAGES = "Images";
    public static final String FIELD_IMAGE_ID = "id";
    public static final String FIELD_IMAGE_NAME = "name";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "(" +
            FIELD_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_USER_NAME + " TEXT," +
            FIELD_USER_EMAIL + " TEXT," +
            FIELD_USER_PASSWORD + " TEXT)";

    private static final String CREATE_DOLLS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DOLLS + "(" +
            FIELD_DOLL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_DOLL_IMAGE_ID + " INTEGER," +
            FIELD_DOLL_NAME + " TEXT," +
            FIELD_DOLL_DESCRIPTION + " TEXT)";

    private static final String CREATE_IMAGES_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + "(" +
            FIELD_IMAGE_ID + " INTEGER PRIMARY KEY," +
            FIELD_IMAGE_NAME + " TEXT)";

    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS;
    private static final String DROP_DOLLS_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOLLS;
    private static final String DROP_IMAGES_TABLE = "DROP TABLE IF EXISTS " + TABLE_IMAGES;

    public DBHelper ( Context context ) {
        super(context , DB_NAME , null , DB_VERSION);
    }


    @Override
    public void onCreate ( SQLiteDatabase db ) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_DOLLS_TABLE);
        db.execSQL(CREATE_IMAGES_TABLE);
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        db.execSQL(DROP_USERS_TABLE);
        db.execSQL(DROP_DOLLS_TABLE);
        db.execSQL(DROP_IMAGES_TABLE);
        onCreate(db);
    }
}
