package com.example.oarrange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    GridView layer0;
    GridView layer1;

    final Integer[][] layers = {
            {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24},
            {0,1,2,3,4,5,6,7,8,9,10}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layer0 = findViewById(R.id.layer0);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, layers[0]);
        layer0.setAdapter(adapter);

        layer1 = findViewById(R.id.layer1);
        adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, layers[1]);
        layer1.setAdapter(adapter);

    }
}