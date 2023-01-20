package com.example.oarrange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.util.Pair;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Card card1 = new Card(0, new Pair<Integer, Integer>(0, 0), 0);

        ImageView mImageView = new ImageView(this);


        FrameLayout mFrameLayout = findViewById(R.id.board);
        mFrameLayout.addView(mImageView);
    }

    static class Card extends AppCompatActivity {

        public Integer type;
        public Pair<Integer, Integer> position;
        public Integer layer;

        public Card(Integer type, Pair<Integer, Integer> position, Integer layer)
        {
            this.type = type;
            this.position = position;
            this.layer = layer;
        }
    }
}