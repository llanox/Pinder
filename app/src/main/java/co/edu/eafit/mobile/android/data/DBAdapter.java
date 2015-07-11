package co.edu.eafit.mobile.android.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    private static final String TAG = DBAdapter.class.getSimpleName();    

    private static final String DATABASE_NAME = "Pinder.db";
    static final int DATABASE_VERSION = 1;
    
    private static final String DATABASE_CREATE =
    "create table "+ProfileData.DATABASE_TABLE+" "+
    "("+ProfileData.KEY_ROWID+" integer primary key autoincrement, "
    + ""+ProfileData.KEY_FULLNAME+" text not null, "+ProfileData.KEY_EMAIL+" text not null);";
    

    private final Context context;
    private DatabaseHelper DBHelper;
    protected SQLiteDatabase db;
    
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version" + oldVersion + " to "+ newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+ProfileData.DATABASE_TABLE);
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    
    
    

}

