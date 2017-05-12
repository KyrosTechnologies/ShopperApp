package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Thirunavukkarasu on 28-07-2016.
 */
public class LoginRepo {
    private final DBHandler dbHandler;
    public LoginRepo(Context context){
        dbHandler= new DBHandler(context);

    }
    public  int insert(Login login){
        SQLiteDatabase db= dbHandler.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Login.UserProfilePicture,login.profilepic);
        values.put(Login.UserEmail,login.email);
        values.put(Login.UserUniqueId,login.useruniqueid);
        values.put(Login.UserName,login.username);

        long login_Id= db.insert(Login.TABLE,null,values);
        db.close();
        return (int)login_Id;

    }
    public void update(Login login){
        SQLiteDatabase db= dbHandler.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Login.UserProfilePicture,login.profilepic);
        values.put(Login.UserEmail,login.email);
        values.put(Login.UserUniqueId,login.useruniqueid);
        values.put(Login.UserName,login.username);
        db.update(Login.TABLE,values,Login.KEY_ID+"="+login.login_ID,null);
        db.close();

    }
    public void delete(){
        SQLiteDatabase db= dbHandler.getWritableDatabase();
        try {
            db.delete(Login.TABLE,"id",new String[] { });        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            db.close();

        }
    }
    public Cursor returndata(){
        SQLiteDatabase db= dbHandler.getReadableDatabase();
        return  db.query(Login.TABLE, new String[] {Login.UserProfilePicture,Login.UserEmail,
                Login.UserUniqueId,Login.UserName},null,null,null,null,null);

    }
}
