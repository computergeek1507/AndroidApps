package com.example.scott.lighttest;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    class TheTask extends AsyncTask<String[],Void,String> {

        @Override
        protected String doInBackground(String[]... arg0) {
            String text = null;
            try {
                String[] inputs = arg0[0];
                String url = "http://"+inputs[0]+":"+inputs[1]+"/rest/items/"+inputs[0];
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(url);
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
