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
import android.util.Log;
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
    private FrameLayout mFrameLayout;
    private FrameLayout.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFrameLayout = findViewById(R.id.board);
        mLayoutParams = new FrameLayout.LayoutParams(dp2px(80, this), dp2px(80, this));

        Card card1 = new Card(0, new Pair<Integer, Integer>(0, 0), 0);
        put(card1, this);
        Card card2 = new Card(0, new Pair<Integer, Integer>(0, 1), 0);
        put(card2, this);
        Card card3 = new Card(0, new Pair<Integer, Integer>(1, 0), 0);
        put(card3, this);
        Card card4 = new Card(0, new Pair<Integer, Integer>(1, 1), 0);
        put(card4, this);
        Card card5 = new Card(0, new Pair<Integer, Integer>(0, 0), 1);
        put(card5, this);
    }



    private static class Card extends AppCompatActivity {
        public Integer type;
        public Pair<Integer, Integer> position;
        public Integer layer;

        public Card(Integer type, Pair<Integer, Integer> position, Integer layer) {
            this.type = type;
            this.position = position;
            this.layer = layer;
        }
    }

    private void put(Card card, Context context) {
        ImageView mImageView = new ImageView(context);
        mImageView.setLayoutParams(mLayoutParams);
        Integer layer = card.layer;
        mImageView.setX(dp2px(layer * 40 + card.position.first * 80, this));
        mImageView.setY(dp2px(layer * 40 + card.position.second * 80, this));
        mImageView.setZ(card.layer);
        mImageView.setImageResource(R.drawable.donut_circle);
        mFrameLayout.addView(mImageView);
    }

    public static int dp2px(int dp, Context context) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}