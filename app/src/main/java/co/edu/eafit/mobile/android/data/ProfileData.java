package co.edu.eafit.mobile.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.mobile.android.model.Profile;

/**
 * Created by jgabrielgutierrez on 15-06-27.
 */
public class ProfileData extends DBAdapter {


    public static final String DATABASE_TABLE ="profiles" ;
    public static final String KEY_ROWID = "_id";
    public static final String KEY_FULLNAME ="fullname" ;
    public static final String KEY_EMAIL ="email" ;


    public ProfileData(Context ctx) {
        super(ctx);
    }


    public boolean save(Profile profile){

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FULLNAME, profile.getFullname());
        initialValues.put(KEY_EMAIL, profile.getEmail());

        long row = db.insertOrThrow(DATABASE_TABLE, null, initialValues);

        return row>0;
    }


    public List<Profile> findAll(){

        List<Profile> profiles = new ArrayList<Profile>();

        Cursor c = db.query(DATABASE_TABLE,
                new String[] {KEY_ROWID, KEY_FULLNAME, KEY_EMAIL},
                null, null, null, null, null);


        Profile profile =null;

        if (c.moveToFirst())
        {
            do {


                profile = new Profile();
                profile.setId(c.getLong(c.getColumnIndex(KEY_ROWID)));
                profile.setFullname(c.getString(c.getColumnIndex(KEY_FULLNAME)));
                profile.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                profiles.add(profile);

            } while (c.moveToNext());
        }

        return profiles;
    }

    public boolean delete(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
