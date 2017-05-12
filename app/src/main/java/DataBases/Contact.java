package DataBases;

import android.icu.text.StringPrepParseException;

/**
 * Created by Rohin on 02-03-2017.
 */

public class Contact {

    private int id;
    private String uniqueid;
    private String name;
    private String email;
    private String profilepic;

    private Contact(){

    }

    public Contact(String uniqueid, String name, String email, String profilepic){
        this.uniqueid=uniqueid;
        this.name=name;
        this.email=email;
        this.profilepic=profilepic;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }
}
