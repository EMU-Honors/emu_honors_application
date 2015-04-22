package edu.emich.honors.emuhonorscollege.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jordan on 4/10/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    //table info
    public static final String LOCAL_DB_NAME = "local_db";
    public static final String TABLE_NAME = "requirements";
    public static final int VERSION = 1;

    //attribute names
    public static final String HANDBOOK_YEAR = "year";
    public static final String HONORS_TYPE = "type";
    public static final String DISPLAY_NUMBER = "dispNumber";
    public static final String REQUIREMENT_NAME = "name";
    public static final String COMPONENT_NAME = "component";
    public static final String TOTAL = "totalNeeded";
    public static final String DESCRIPTION = "description";
    public static final String TOTAL_COMPLETED = "completed";

    //create statement
    public String CREATE_QUERY = "CREATE TABLE " +
            TABLE_NAME + "(" +
            HANDBOOK_YEAR + " TEXT," +
            HONORS_TYPE + " TEXT," +
            DISPLAY_NUMBER + " INTEGER," +
            REQUIREMENT_NAME + " TEXT," +
            COMPONENT_NAME + " TEXT," +
            TOTAL + " INTEGER," +
            DESCRIPTION + " TEXT," +
            TOTAL_COMPLETED + " TEXT, " +
            "PRIMARY KEY(year, type, dispNumber, name));";


    public SQLiteHelper(Context context) {
// Database: local_db, Version: 1
        super(context, LOCAL_DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("create", "DB CREATED");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d("upgrade", "UPGRADED TABLE");

    }

    // insert a requirement -> must parse JSON and change dispNumber & totalNeeded to int
// completed is set at 0 (false)
    public boolean insertRequirement(String year, String type, int dispNumber, String name,
                                     String component, int totalNeeded, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("year", year);
        contentValues.put("type", type);
        contentValues.put("dispNumber", dispNumber);
        contentValues.put("name", name);
        contentValues.put("component", component);
        contentValues.put("totalNeeded", totalNeeded);
        contentValues.put("description", description);
        contentValues.put("completed", 0);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }


}
