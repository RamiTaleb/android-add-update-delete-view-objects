package com.example.rami.picturetester;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

import java.sql.SQLException;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "things.db";
    public static final String TABLE_PRODUCTS = "things";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT ," +
                COLUMN_IMAGE + " BLOB " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void insertData(String name, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO THINGS VALUES (NULL, ?, ?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public void updateData(int id, String name, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "UPDATE THINGS SET name = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindBlob(2, image);
        statement.bindDouble(3, (double)id);

        statement.execute();
        db.close();
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }

    public void deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM THINGS WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        db.close();
    }

}
