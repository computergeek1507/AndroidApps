package com.example.scott.lighttest;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class MainActivity extends ActionBarActivity
{
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Button p1_button = (Button)findViewById(R.id.button1);
        p1_button.setText(preferences.getString("button1_text", "Button 1 Text"));
        Button p2_button = (Button)findViewById(R.id.button2);
        p2_button.setText(preferences.getString("button2_text", "Button 2 Text"));
        Button p3_button = (Button)findViewById(R.id.button3);
        p3_button.setText(preferences.getString("button3_text", "Button 3 Text"));
        Button p4_button = (Button)findViewById(R.id.button4);
        p4_button.setText(preferences.getString("button4_text", "Button 4 Text"));
        Button p5_button = (Button)findViewById(R.id.button5);
        p5_button.setText(preferences.getString("button5_text", "Button 5 Text"));
        Button p6_button = (Button)findViewById(R.id.button6);
        p6_button.setText(preferences.getString("button6_text", "Button 6 Text"));
        Button p7_button = (Button)findViewById(R.id.button7);
        p7_button.setText(preferences.getString("button7_text", "Button 7 Text"));
        Button p8_button = (Button)findViewById(R.id.button8);
        p8_button.setText(preferences.getString("button8_text", "Button 8 Text"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Intent settingsBrowser = new Intent(MainActivity.this, SettingsActivity.class);
            settingsBrowser.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            MainActivity.this.startActivity(settingsBrowser);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button1Click(View view)
    {
        String[] button1Arg = new String[2];
        button1Arg[0] = preferences.getString("button1_key", "Light1");
        button1Arg[1] = preferences.getString("button1_value", "ON");
        new ServerTask().execute(button1Arg);
    }

    public void button2Click(View view)
    {
        String[] button2Arg = new String[2];
        button2Arg[0] = preferences.getString("button2_key", "Light2");
        button2Arg[1] = preferences.getString("button2_value", "ON");
        new ServerTask().execute(button2Arg);
    }

    public void button3Click(View view)
    {
        String[] button3Arg = new String[2];
        button3Arg[0] = preferences.getString("button3_key", "Light3");
        button3Arg[1] = preferences.getString("button3_value", "ON");
        new ServerTask().execute(button3Arg);
    }

    public void button4Click(View view)
    {
        String[] button4Arg = new String[2];
        button4Arg[0] = preferences.getString("button4_key", "Light4");
        button4Arg[1] = preferences.getString("button4_value", "ON");
        new ServerTask().execute(button4Arg);
    }

    public void button5Click(View view)
    {
        String[] button5Arg = new String[2];
        button5Arg[0] = preferences.getString("button5_key", "Light1");
        button5Arg[1] = preferences.getString("button5_value", "OFF");
        new ServerTask().execute(button5Arg);
    }

    public void button6Click(View view)
    {
        String[] button6Arg = new String[2];
        button6Arg[0] = preferences.getString("button6_key", "Light2");
        button6Arg[1] = preferences.getString("button6_value", "OFF");
        new ServerTask().execute(button6Arg);
    }

    public void button7Click(View view)
    {
        String[] button7Arg = new String[2];
        button7Arg[0] = preferences.getString("button7_key", "Light3");
        button7Arg[1] = preferences.getString("button7_value", "OFF");
        new ServerTask().execute(button7Arg);
    }

    public void button8Click(View view)
    {
        String[] button8Arg = new String[2];
        button8Arg[0] = preferences.getString("button8_key", "Light4");
        button8Arg[1] = preferences.getString("button8_value", "OFF");
        new ServerTask().execute(button8Arg);
    }

    private void updateStatus(String item,String state)
    {
        try
        {
            String address = "";
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(address);

            HttpResponse response = client.execute(post);
        }
        catch (Exception e)
        {
            Log.d("InputStream", e.getLocalizedMessage());
        }
    }
    //new ServerTask().execute(url1, Void, url3);



    class ServerTask extends AsyncTask<String[],Void,String> {

        @Override
        protected String doInBackground(String[]... arg0) {
            String text = null;
            try {
                String serverIP = preferences.getString("server_ip", "192.168.5.148");
                String serverPort = preferences.getString("server_port", "8080");

                String[] inputs = arg0[0];
                String url = "http://"+serverIP+":"+serverPort+"/rest/items/"+inputs[0];
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
                httppost.setHeader("Content-Type", "text/plain");
                httppost.setEntity(new StringEntity(inputs[1]));
                HttpResponse resp = httpclient.execute(httppost);
                HttpEntity ent = resp.getEntity();
                text = EntityUtils.toString(ent);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return text;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

        }
    }
}
