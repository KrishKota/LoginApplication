package com.example.kota.loginapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        textViewFName.setText("Welcome"+ nameFromIntent);

    }
}
