package co.edu.eafit.mobile.android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import co.edu.eafit.mobile.android.R;


public class ProfileView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }

        String userName = extras.getString(LoginActivity.CURRENT_USER);

        String msg = String.format("Welcome %s ",userName);
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_view, menu);
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

        if(id == R.id.action_logout){

            SharedPreferences preferences =
            this.getSharedPreferences(LoginActivity.SHARED_DATA,MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.clear();
            edit.commit();

            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
