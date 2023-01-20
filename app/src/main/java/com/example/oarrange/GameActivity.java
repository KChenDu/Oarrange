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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        FrameLayout mFrameLayout = findViewById(R.id.board);

        // Card card1 = new Card(0, new Pair<Integer, Integer>(0, 0), 0);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dp2px(80, this), dp2px(80, this));

        // Add first icon
        ImageView mImageView1 = new ImageView(this);
        mImageView1.setLayoutParams(params);
        mImageView1.setZ(0);
        mImageView1.setImageResource(R.drawable.donut_circle);
        mFrameLayout.addView(mImageView1);

        // Add second icon
        ImageView mImageView2 = new ImageView(this);
        mImageView2.setLayoutParams(params);
        mImageView2.setX(dp2px(80, this));
        mImageView2.setZ(0);
        mImageView2.setImageResource(R.drawable.icecream_circle);
        mFrameLayout.addView(mImageView2);

        // Add third icon above the previous two
        ImageView mImageView3 = new ImageView(this);
        mImageView3.setLayoutParams(params);
        mImageView3.setX(dp2px(40, this));
        mImageView3.setY(dp2px(40, this));
        mImageView3.setZ(1);
        mImageView3.setImageResource(R.drawable.froyo_circle);
        mFrameLayout.addView(mImageView3);

        // Add fourth icon
        ImageView mImageView4 = new ImageView(this);
        mImageView4.setLayoutParams(params);
        mImageView4.setY(dp2px(80, this));
        mImageView4.setZ(0);
        mImageView4.setImageResource(R.drawable.ic_launcher_background);
        mFrameLayout.addView(mImageView4);

        // Add fifth icon
        ImageView mImageView5 = new ImageView(this);
        mImageView5.setLayoutParams(params);
        mImageView5.setX(dp2px(80, this));
        mImageView5.setY(dp2px(80, this));
        mImageView5.setZ(0);
        mImageView5.setImageResource(R.drawable.icecream_circle);
        mFrameLayout.addView(mImageView5);
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

    public static int dp2px(int dp, Context context) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}