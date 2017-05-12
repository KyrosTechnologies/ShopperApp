package DataBase;

/**
 * Created by Thirunavukkarasu on 28-07-2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thirunavukkarasu on 27-04-2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Shopperapp";




    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Login.TABLE + "("
                + Login.KEY_ID + " INTEGER PRIMARY KEY, "
                + Login.UserUniqueId + " TEXT,"
                + Login.UserEmail + " TEXT," + Login.UserName + " TEXT,"
                + Login.UserProfilePicture + " TEXT " +")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE);
        onCreate(db);
    }

}