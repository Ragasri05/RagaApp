package com.example.foodorderapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// step 1 is create the Database to Json Convertor.
public class DatabaseToJsonConvertor {
    // Json exception handles errors if something goes Wrong while converting the database to Json format.
    // IOException is used to handle the issues while saving the file.
    public static File convertDatabaseToJson(Context context, String databaseName) throws JSONException, IOException {
        // opening an existing database or creating a new one with the name provided in databaseName.
        // Context.MODE_PRIVATE Ensures that the database is private to your app
        // CursorFactory is an interface that allows you to customize how Cursor objects are created when you perform database queries.
        // null means we are using the default Cursor.
        SQLiteDatabase database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        // Cursor is like a pointer that iterates through each row in the pointer. null because initially not assigning any variable.
        Cursor cursor = null;
        // JsonArray will store the data which is present in the tables in Json Format.
        JSONArray jsonArray = new JSONArray();
        // running a SQLite query to get all the names of all the tables in the database.
        // sqlite_master: A system table in SQLite that keeps metadata about tables.
        // type='table': Filters only the tables
        // and the result is stored in the cursor.
        try {
            // cursor stores the names of the Tables.
            cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            // moveToFirst() means the cursor will move to the first name of the Table.
            // if the Table doesn't exist, then the condition is false.
            if (cursor.moveToFirst()){
                // loops through all the tables, fetches the data from each table and converts it Json file.
                do {
                    // retrieves the table Name from the first column of the cursor.
                    String tableName = cursor.getString(0);
                    // android_metadata: Stores metadata about the database. It's not a user-defined table.
                    // sqlite_sequence: Stores information about auto-incremented values for tables.
                    // thses tables are skipped beacuse they don't contain any meaning ful data.
                    if (!tableName.equals("android_metadata") && !tableName.equals("sqlite_sequence")) {
                        // fetches all the rows and tables from current table and stores in the tableCursor.
                        Cursor tableCursor = database.rawQuery("SELECT * FROM " + tableName, null);
                        // a Json array is created to hold all the rows of the current Table.
                        JSONArray tableArray = new JSONArray();
                        // moves to the next row and the loop stops when there are no more loops.
                        while (tableCursor.moveToNext()) {
                            // creating a Json object to represent a single row in the table.
                            JSONObject rowObject = new JSONObject();
                            // loop through Coloumns.
                            // getColumnCount() gets the total number of columns in the table.
                            for (int i = 0; i < tableCursor.getColumnCount(); i++) {
                                // getColumnName becomes the Json Key and gatString becomes the Json value.
                                rowObject.put(tableCursor.getColumnName(i), tableCursor.getString(i));
                                // the Json object would be like this. ---> {"id": "1", "name": "Raga", "age": "17"}
                            }
                            // putting each row in the tableArray
                            tableArray.put(rowObject);
                        }
                        //This means you are telling the system that you're done using it, and it can now free up the memory or resources that were being used by that cursor.
                        tableCursor.close();
                        // creating a Json object to represent the entire Table.
                        // tableObject will store tableName as the Key and tableArray as Value.
                        JSONObject tableObject = new JSONObject();
                        tableObject.put(tableName, tableArray);
                        // adding the tables Json Object to main Json Array.
                        jsonArray.put(tableObject);
                    }
                }while (cursor.moveToNext()) ; //loop is continued till all tables are processed.
            }
        }finally {
            // closing the main cursor and database to prevent dataLoss.
            if (cursor != null){
                cursor.close();
                database.close();
            }
        }
        // creating a file object.
        // context.getFilesDir --> retrieves the internal storage of the app.
        File jsonFile = new File(context.getFilesDir()+databaseName+".json");
        // Using a file writer, you write data into the Json file.
        FileWriter writer = new FileWriter(jsonFile);
        writer.write(jsonArray.toString());
        writer.close();

        return jsonFile;
    }
}
