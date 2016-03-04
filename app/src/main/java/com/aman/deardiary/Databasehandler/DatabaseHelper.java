package com.aman.deardiary.Databasehandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aman on 30/12/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "DiaryDB";

    //Table Name
    private static final String TABLE_NAME = "Entries";

    //Table Columns
    private static final String CREATED_AT = "Created_At";
    private static final String DATE_OF_ENTRY = "Date_Of_Entry";
    private static final String ENTRY_CONTENTS = "Contents";

    private static final String CREATE_NEW_TABLE = "CREATE TABLE " + TABLE_NAME + "( " +
            CREATED_AT + " DATE, " + DATE_OF_ENTRY + " DATE, " + ENTRY_CONTENTS
            + " VARCHAR(1000)" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        TODO: Delete this line before app completion
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        db.execSQL(CREATE_NEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long createNewEntry(DiaryEntry diaryEntry) {
        /*
        TODO: Delete previous entry already existing for the current date
         */
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        /*String checkprev = "SELECT * FROM " + TABLE_NAME + " WHERE " + DATE_OF_ENTRY +
                "=\'" + sdf.format(diaryEntry.getDate())+"\'";

        Cursor cursor = db.rawQuery(checkprev, null);
        if(cursor!=null && cursor.moveToFirst()) {
            db.delete(TABLE_NAME, DATE_OF_ENTRY + "=?", new String[]{sdf.format(diaryEntry.getDate())});
        }*/

        values.put(CREATED_AT, sdf.format(diaryEntry.getCreatedAt()));
        values.put(DATE_OF_ENTRY, sdf.format(diaryEntry.getDate()));
        values.put(ENTRY_CONTENTS, diaryEntry.getContent());

        long entry_id = db.insertOrThrow(TABLE_NAME, null, values);

        db.close();
        return entry_id;
    }

    public DiaryEntry getDiaryEntry(Date date) {

        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + DATE_OF_ENTRY +
                "=\'" + sdf.format(date)+"\'";
        Log.i(LOG, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);

        DiaryEntry de = new DiaryEntry();
        if(c != null && c.moveToFirst())
        {
            de.setDate(date);
            de.setContent(c.getString(c.getColumnIndex(ENTRY_CONTENTS)));
            try {
                de.setCreatedAt(sdf.parse(c.getString(c.getColumnIndex(CREATED_AT))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Log.i(LOG, de.getContent()+sdf.format(de.getDate()));

        if (c != null) {
            c.close();
        }
        db.close();
        return de;
    }

    public List<DiaryEntry> getAllEntries() {
        List<DiaryEntry> entries = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + CREATED_AT + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        DiaryEntry entry = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(cursor!=null && cursor.moveToFirst()) {
            do{
                entry = new DiaryEntry();
                try {
                    entry.setDate(sdf.parse(cursor.getString(cursor.getColumnIndex(DATE_OF_ENTRY))));
                    entry.setCreatedAt(sdf.parse(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                entry.setContent(cursor.getString(cursor.getColumnIndex(ENTRY_CONTENTS)));
                entries.add(entry);
            }while(cursor.moveToNext());

            cursor.close();
        }
        return entries;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
