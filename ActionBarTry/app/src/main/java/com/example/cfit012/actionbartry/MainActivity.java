package com.example.cfit012.actionbartry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        JSONObject json=new JSONObject();

        try
        {
            json.put("event_id","1");

        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        String serverURL="http://192.168.3.3:8080/read";
        TextView answer=(TextView)findViewById(R.id.read_content);
        PostJson postJson=new PostJson(json,answer);
        postJson.execute(serverURL);


        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);

            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void Update()
    {
        Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
        JSONObject data=new JSONObject();
        try{
            data.put("event_id","1");
            data.put("name","RASHIK BHASIN");
            data.put("event_info","INFO 1");
            data.put("venue","VENUE 1");
            data.put("date","2018-08-02");
            data.put("city","delhi");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        String sending=data.toString();
        intent.putExtra("data",sending);
        startActivity(intent);
    }

    public void Delete()
    {
        Intent intent=new Intent(MainActivity.this,DeleteActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_delete:
                // search action
                Delete();
                return true;
            case R.id.action_update:
                // location found
                Update();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);


            //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
        }
    }
}
