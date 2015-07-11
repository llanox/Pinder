package co.edu.eafit.mobile.android.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import co.edu.eafit.mobile.android.R;
import co.edu.eafit.mobile.android.data.ProfileData;
import co.edu.eafit.mobile.android.model.Profile;


public class ProfileList extends ActionBarActivity {

    private ProfileData profileData;

    @Override
    protected void onResume() {
        super.onResume();

        profileData.open();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileData.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        profileData = new ProfileData(this);
        profileData.open();

        ListView profileList = (ListView)this.findViewById(R.id.lv_profiles);

        List<Profile> profiles = profileData.findAll();

        ArrayAdapter<Profile> arrayAdapter = new ArrayAdapter<Profile>(this,
                android.R.layout.simple_list_item_1,profiles);

        profileList.setAdapter(arrayAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
