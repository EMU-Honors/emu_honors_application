import android.provider.BaseColumns;

/**
 * Created by Jordan on 4/6/2015.
 *
 * This class just stores some information about the local table.
 * LocalDatabaseOperations has the actual queries
 *
 */
public class LocalTableInfo {

  //empty default constructor (said to avoid some problems)
    public LocalTableInfo(){

    }


  //inner class -> basecol gives _ID auto increment
    public static abstract class TableInfo implements BaseColumns{

       public static final String REQ_NAME = "name" ;
       public static final String DESCRIPTION = "description" ;
       public static final int COMPLETED = 0 ;  //bool -> 0 or 1
       public static final int NUMBER_OF_COMPLETED = 0 ;
       public static final int NUMBER_REQUIRED_FOR_COMPLETION = 0 ;

       public static final String DATABASE_NAME = "localHonors" ;
       public static final String TABLE_NAME = "requirements" ;

    }

}
