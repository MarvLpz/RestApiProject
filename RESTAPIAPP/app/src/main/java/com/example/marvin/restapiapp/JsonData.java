package com.example.marvin.restapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JsonData extends AppCompatActivity {

    TextView jsonTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data);

        jsonTextView = (TextView) findViewById(R.id.json_tv_title_id);
        Intent intent = getIntent();
        String intentString = intent.getExtras().getString("Title_intent");
        jsonTextView.setText(intentString);

    }
}
