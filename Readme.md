# Owner 
Register/Login --> Add/Update Menu --> Sinc Menu to Firebase cloud.
# Customer
Scan the Qr code --> View the menu --> add items to the cart --> Enter the mobile_Number and Name -->  Place Order.
# owner 
receives the order along with name and mobilenumber --> once food is prepared the owner will click a button and this will send message to Cutomer's mobile number.
# Customer 
receives a message "Order done!"
# Steps:
- Make a login and register page
- The owner must use apps UI to add menu items. This data will be saved locally to the apps Roomdatabase.
- make the layout.
- ![Screenshot from 2025-01-05 13-12-53](https://github.com/user-attachments/assets/71201d0c-2509-48c3-9296-178125560ad9)
- Make an Entity class.(java class) { Entity class represents a table in the database}
- Make a Dao Interface.
- Make a Database class.

## Make a new Activity for Fetching Data. name= GetMenu
- Add dependencies for recyclerview and cardView.
- Make a linear Layout file with Vertical orientation and add recyclerview.
- In GetMenu java file,make an object for recyclerview and using findViewByIdMethod, link the recyclerView to the xml file.
- set the Layput Manager for the recycler view.
```
recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
```
# to COnvert Databse to Json.
- Step1 : 
```
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

```
- Step2:
  * to serve Json file Locally.
  * Add NanoHTTPD to Your Project
  *  This will allow you to generate a URL like http://localhost:8080 that other parts of your app (like Retrofit) can use to fetch the file.
  *  add the nanoHTTPD dependency
```
    implementation (libs.nanohttpd)
```
- Step3:
  * to use the nanoHTTPD we must create a server class which serves the Json file.
```
package com.example.foodorderapp;

import java.io.File;
import java.io.FileInputStream;

import fi.iki.elonen.NanoHTTPD;

// NanoHTTPD is a library for creating HTTP severs.
public class JsonHttpServer extends NanoHTTPD {

    private final File jsonFile;
    public JsonHttpServer(int port, File jsonFile) {
        super(port); // the port number to which the server will listen.
        this.jsonFile = jsonFile;
    }

    @Override
    // this method is called whenever the server receives Http request.
    // IHTTPSession session Contains details of the incoming HTTP request.
    public Response serve(IHTTPSession session) {
        return super.serve(session);
        try{
            // FileInputStream is a class in java.io package.
            // it is used to read data from the files.
            FileInputStream fileInputStream = new FileInputStream(jsonFile);
            // newFixedLengthResponse creates a http response.
            //parameters are status,content type (o indicate that the response is a JSON file), fileContent (reads the file using fileInputStream,file length.
            return newFixedLengthResponse(Response.Status.OK,"application/json",fileInputStream,jsonFile.length());
        }catch (Exception e){
            //logs the error message
            e.printStackTrace();
            return newFixedLengthResponse(Response.Status.INTERNAL_ERROR,"text/plain", "Error serving file")
        }
    }
}
```




