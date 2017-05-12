package DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

/**
 * Created by Rohin on 02-03-2017.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "details";
    private static final String TABLE_CONTACTS = "userprofile";

    private static final String KEY_ID = "id";
    private static final String KEY_UNIQUE = "uniqueid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL= "email";
    private static final String KEY_PROFILEPIC = "profilepic";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_DETAIL = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UNIQUE + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PROFILEPIC + " TEXT" + ")";
        db.execSQL(CREATE_USER_DETAIL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UNIQUE, contact.getUniqueid());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_PROFILEPIC, contact.getProfilepic());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_UNIQUE, KEY_NAME, KEY_EMAIL, KEY_PROFILEPIC },
                 KEY_ID + "=?",new String[] { String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact((cursor.getString(1)),
                 cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return contact;
    }

    public  int getContactsCount(){
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return  cursor.getCount();
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_UNIQUE, contact.getUniqueid());
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_PROFILEPIC, contact.getProfilepic());
        return db.update(TABLE_CONTACTS, values, KEY_ID + " =?",new String[] { String.valueOf(contact.getId()) });
    }
    public void deleteContact(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID ,new String[] {  });
        db.close();
    }
}
