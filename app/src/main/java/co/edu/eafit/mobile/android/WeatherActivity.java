package co.edu.eafit.mobile.android;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import co.edu.eafit.mobile.android.R;


public class WeatherActivity extends ActionBarActivity {
    private static final String URL_QUERY="http://api.openweathermap.org/data/2.5/weather?q=%s";



    public TextView mProgressText;
    public EditText mQuery;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        invocarServicio(null);

    }





    //Cuando muestra la actividad
    @Override
    protected void onResume() {
        super.onResume();

    }

    //Cuando temrina la actividad y necesita liberar recursos
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }





    public void invocarServicio(View view) {

        EditText edCiudad = (EditText) this.findViewById(R.id.et_city);
        final String ciudad = edCiudad.getText().toString();

        final TextView temperature = (TextView) this.findViewById(R.id.tv_temp);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                final String temp = queryToAPI(ciudad);


                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        temperature.setText(" "+temp+" Fº");

                    }
                });

            }
        });

        thread.start();


    }


    String queryToAPI(final String ciudad) {
        String temp =null;
        try {

            String query = String.format(URL_QUERY, URLEncoder.encode(ciudad, "utf-8"));

            //set the download URL, a url that points to a file on the internet
            //this is the file to be downloaded
            URL url = new URL(query);

            //create the new connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //set up some things on the connection
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //and connect!
            urlConnection.connect();

            //this will be used in reading the data from the internet
            String response = urlConnection.getResponseMessage();
            InputStream data = (InputStream) urlConnection.getContent();

            InputStreamReader is = new InputStreamReader(data);
            StringBuilder sb=new StringBuilder();
            BufferedReader br = new BufferedReader(is);
            String read = br.readLine();

            while(read != null) {
                //System.out.println(read);
                sb.append(read);
                read = br.readLine();

            }




            JSONObject object = new JSONObject(sb.toString());
            JSONObject main = (JSONObject) object.get("main");
            temp = main.getString("temp");
            Log.d("Result", temp);





            //catch some possible errors...
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return temp;
    }


    class DownloadData extends AsyncTask<String,Long,String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            TextView temperature = (TextView) WeatherActivity.this.findViewById(R.id.tv_temp);
            temperature.setText("Iniciando consulta del clima...");
        }



        @Override
        protected String doInBackground(String... params) {


            long kilobytes= 100;
            do{

                kilobytes++;
                publishProgress(kilobytes);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }while(kilobytes<=12000);

            return null;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            TextView temperature = (TextView) WeatherActivity.this.findViewById(R.id.tv_temp);
            temperature.setText(result+"Kº Es la temperatura actual");
        }





    }




}
