package com.example.latihanuts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private Context ctx;
//    inisiasi database nya
    private static final String DATABASE_NAME = "db_catatan";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_catatan";
    private static final String FIELD_ID = "id";
    private static final String FIELD_JUDUL = "judul";
    private static final String FIELD_ISI = "isi";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

//        membuat query create tabel
        String queri = "CREATE TABLE " + TABLE_NAME + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_JUDUL + " VARCHAR(16), " +
                FIELD_ISI + " VARCHAR(250) ); ";

db.execSQL(queri);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queri = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(queri);
        onCreate(db);
    }

public long tambahKonten(String judul, String isi){
SQLiteDatabase db =this.getWritableDatabase();
    ContentValues cv =new ContentValues();

    cv.put(FIELD_JUDUL, judul);
    cv.put(FIELD_ISI, isi);

    long masukan =db.insert(TABLE_NAME, null,cv);
return masukan;
}

public Cursor bacaKonten(){
SQLiteDatabase db = this.getReadableDatabase();
String queri = "SELECT * FROM " + TABLE_NAME;
    Cursor varCursor =null;
    if (db != null){
varCursor =db.rawQuery(queri, null);
    }
    return varCursor;
}

}
