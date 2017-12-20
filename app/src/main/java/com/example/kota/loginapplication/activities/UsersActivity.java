package com.example.kota.loginapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kota.loginapplication.R;

/**
 * Created by kota on 12/19/2017.
 */

public class UsersActivity extends AppCompatActivity{

    private TextView textViewFName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users);

        textViewFName= (TextView) findViewById(R.id.tv1);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewFName.setText("Welcome "+ nameFromIntent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // use an inflater to populate the ActionBar with items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_log_out, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // same as using a normal menu
        switch(item.getItemId()) {
            case R.id.action_logout:
                finish();
               makeToast("Successfully!! Logged Out..");
                break;
        }
        return true;
    }
    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}
