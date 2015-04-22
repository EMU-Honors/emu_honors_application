import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jordan on 4/6/2015.
 *
 * Currently leaving out sub requirements for trial. I am not sure if we should create one table
 * for main requirements and one table for sub requirements or attempt to place them together??
 *
 * NOTE: I HAVE NOT TOUCHED THE ANDROID MANIFEST -> LOCALTABLEINFO AND THIS CLASS ARE NOT IN
 */
public class LocalDatabaseOperations extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ LocalTableInfo.TableInfo.TABLE_NAME+"("+
            LocalTableInfo.TableInfo.REQ_NAME+" TEXT,"+
            LocalTableInfo.TableInfo.DESCRIPTION+" TEXT,"+
            LocalTableInfo.TableInfo.COMPLETED+" INTEGER,"+   //bool  ->  0 or 1
            LocalTableInfo.TableInfo.NUMBER_OF_COMPLETED +" INTEGER,"+
            LocalTableInfo.TableInfo.NUMBER_REQUIRED_FOR_COMPLETION+" INTEGER );" ;



    public LocalDatabaseOperations(Context context) {

      // factory (second parameter) = null
        super(context, LocalTableInfo.TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database created");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

  //this will be called when importing data from REMOTE DB
  //may be able to hard code some data into the table for testing until we get more from Eddie
    public void insertIntoTable(LocalDatabaseOperations dop, String name, String desc,
                                       int complete, int numComplete, int numRequired)  {

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(LocalTableInfo.TableInfo.REQ_NAME, name);
        vals.put(LocalTableInfo.TableInfo.DESCRIPTION, desc);
        vals.put(String.valueOf(LocalTableInfo.TableInfo.COMPLETED), complete);
        vals.put(String.valueOf(LocalTableInfo.TableInfo.NUMBER_OF_COMPLETED), numComplete);
        vals.put(String.valueOf(LocalTableInfo.TableInfo.NUMBER_REQUIRED_FOR_COMPLETION), numRequired);

     //may want to remove long k if does not work
        long k = SQ.insert(LocalTableInfo.TableInfo.TABLE_NAME, null, vals);
        Log.d("Database operations", "One row inserted");
    }




}  //end class
