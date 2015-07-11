package co.edu.eafit.mobile.android.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.eafit.mobile.android.data.ProfileData;
import co.edu.eafit.mobile.android.model.Profile;
import co.edu.eafit.mobile.android.R;


public class RegisterActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    public static final String REGISTER_ACTIVITY = RegisterActivity.class.getClass().getSimpleName();
    private ProfileData profileData;

    @Override
    protected void onResume() {
        super.onResume();
        profileData = new ProfileData(this);
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
        setContentView(R.layout.activity_register);

       String[] cities = this.getResources().getStringArray(R.array.cities);
       Spinner spCities = (Spinner) this.findViewById(R.id.sp_cities);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,cities);


        spCities.setAdapter(arrayAdapter);
        spCities.setOnItemSelectedListener(this);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Log.d(REGISTER_ACTIVITY,"Adapter "+parent);

        if(parent.getId() == R.id.sp_cities ) {

            String[] cities = this.getResources().getStringArray(R.array.cities);
            Toast.makeText(this, String.format("Position %s %s", position, cities[position]), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void saveProfile(View view){

        Profile profile = new Profile();

        TextView tv = (TextView) this.findViewById(R.id.et_fullname);
        String fullName = tv.getText().toString();

        profile.setFullname(fullName);

        tv = (TextView) this.findViewById(R.id.et_email);
        String email = tv.getText().toString();

        profile.setEmail(email);

        if(profileData.save(profile)){
            Toast.makeText(this,"Saved!!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ProfileList.class);
            this.startActivity(intent);

        }else{
            Toast.makeText(this,"Error!!",Toast.LENGTH_LONG).show();

        }






    }
}
