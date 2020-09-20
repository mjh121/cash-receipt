package com.example.dodu.cashreceipt.customerDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dodu on 15. 5. 18..
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "costomer.db"; // Database name
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDB;


    public MySQLiteHelper(Context context) {
        // super(context, name, factory, version)
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create payer table
        db.execSQL(DataBases.CreateDB._CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // version UpDate, DataBase Regenerate
        db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME);
        onCreate(db);
    }

    public void open() throws SQLException {
        // DB open
        // get reference to readable DB
        mDB = this.getWritableDatabase();
    }


    public long insertColumn(String name, String contact, String payments) {
        // insert DB
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.CONTACT, contact);
        values.put(DataBases.CreateDB.PAYMENTS, payments);
        return mDB.insert(DataBases.CreateDB._TABLENAME, null, values);
    }

    public boolean deleteColumn(long id) {
        // Delete _id
        return mDB.delete(DataBases.CreateDB._TABLENAME, "_id="+id, null) > 0;
    }

    public boolean deleteColumn(String name) {
        // Delete Name
        return mDB.delete(DataBases.CreateDB._TABLENAME, "name="+name, null) > 0;
    }

    public Cursor getAllColumn() {
        return mDB.query(DataBases.CreateDB._TABLENAME, null, null, null, null, null, null);
    }
}