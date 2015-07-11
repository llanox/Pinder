package co.edu.eafit.mobile.android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.eafit.mobile.android.R;
import co.edu.eafit.mobile.android.WeatherActivity;


public class LoginActivity extends ActionBarActivity {


    public static final String CURRENT_USER = "current_user";
    public static final String ACTIVE_USER = "activeUser";
    public static final String SHARED_DATA = "shared_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = this.getSharedPreferences(SHARED_DATA,MODE_PRIVATE);
        String activeUser = preferences.getString(ACTIVE_USER, null);

        if(activeUser!=null){
            Intent changePage = new Intent(this,ProfileView.class);
            changePage.putExtra(CURRENT_USER,activeUser);
            this.startActivity(changePage);

        }

    }


    public void goToProfileView(View view){

        EditText etUser = (EditText) this.findViewById(R.id.et_user);
        String user = etUser.getText().toString();

        EditText etPasword = (EditText) this.findViewById(R.id.et_password);
        String password = etPasword.getText().toString();

        if(!authenticate(user,password)){

            Toast.makeText(this,"Wrong user or password",Toast.LENGTH_LONG).show();;
            return;
        }

        SharedPreferences preferences =  this.getSharedPreferences(SHARED_DATA,MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        edit.putString(ACTIVE_USER, user);
        edit.apply();




        Intent changePage = new Intent(this,ProfileView.class);
        changePage.putExtra(CURRENT_USER,user);

        this.startActivity(changePage);


    }

    private boolean authenticate(String user, String password) {

        if("juang".compareTo(user) == 0 &&
           "12345".compareTo(password) == 0){

            return true;
        }

        return false;
    }

    public void goToRegisterView(View view){

        Intent registerPage = new Intent(this,RegisterActivity.class);
        this.startActivity(registerPage);
    }

    public void goToWeather(View view){

        Intent weatherPage = new Intent(this,WeatherActivity.class);
        this.startActivity(weatherPage);
    }



}
