package com.example.foodorderapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class ContentProvider extends android.content.ContentProvider {

    static final String id = "id";
    static final String feedback = "feedback";


    static final String authorityName = "com.example.foodorderapp.provider";
    static final String URL = "content://" + authorityName + "/feedbacktable";

    static final Uri contentUri = Uri.parse(URL);

    static final int uriCode = 1;
    static  UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authorityName,"feedbacktable", uriCode);
        uriMatcher.addURI(authorityName,"feedbacktable/*",uriCode);
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
        if (db!=null)
            return true;
        else
            return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tableName);
        switch (uriMatcher.match(uri)){
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("unknown uri"+uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = id;
        }
        Cursor c = qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }


    @Nullable
    @Override
    // returns a MIME format that describes the type of data returned by the content uri.
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case uriCode:
                return "vnd.android.cursor.dir/users";
            default:
                throw new IllegalArgumentException("unknown uri");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId = db.insert(tableName,"",values);
        if (rowId > 0 ){
            Uri _uri = ContentUris.withAppendedId(contentUri,rowId);
            getContext().getContentResolver().notifyChange(uri,null);
            return _uri;
        }
        throw new SQLiteException("failed to add record" + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }



    // Creating an Object of Database.
    private SQLiteDatabase db;
    static final String databaseName = "feedback";
    static final String tableName = "feedbackTable";
    // version helps in creating snapshot of data at
    static final int databaseVersion = 1;
    // sql query to create the table
    static final String CreateTable = " CREATE TABLE " + tableName
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " feedback TEXT NOT NULL) ";

    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(@Nullable Context context ) {
            super(context, databaseName, null,databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+tableName+" (id INTEGER PRIMARY KEY AUTOINCREMENT,  feedback TEXT NOT NULL) ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
            onCreate(db);
        }
    }
}
