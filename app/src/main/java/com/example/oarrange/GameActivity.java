package com.example.oarrange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
/*
        GridLayout gridLayout = new GridLayout(this);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = GridLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = GridLayout.LayoutParams.MATCH_PARENT;
        layoutParams.setMargins(0, 0, 0, 0);
        gridLayout.setLayoutParams(layoutParams);
        gridLayout.setColumnCount(5);
        gridLayout.setColumnCount(5);
 */
        //ImageView imageView = new ImageView(this);
        //imageView.setImageResource(R.drawable.donut_circle);
        //imageView.setLayoutParams(new ViewGroup.LayoutParams(80, 80));
/*
        GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
        GridLayout.Spec colSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
        GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                rowSpan, colSpan
        );*/

        // GridLayout layer0 = (GridLayout) findViewById(R.id.layer0);

        // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, );

    }
}