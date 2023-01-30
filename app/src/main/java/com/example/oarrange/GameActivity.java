package com.example.oarrange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;
    private FrameLayout.LayoutParams mLayoutParams;
    private HashMap<Integer, Integer> id2img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFrameLayout = findViewById(R.id.board);
        mLayoutParams = new FrameLayout.LayoutParams(dp2px(80, this), dp2px(80, this));
        id2img = new HashMap<Integer, Integer>();
        id2img.put(0, R.drawable.orange);
        id2img.put(1, R.drawable.donut_circle);
        id2img.put(2, R.drawable.froyo_circle);
        id2img.put(3, R.drawable.ic_launcher_foreground);
        id2img.put(4, R.drawable.icecream_circle);

        Card card1 = new Card(0, new Pair<Integer, Integer>(0, 0), 0);
        put(card1, this);
        Card card2 = new Card(1, new Pair<Integer, Integer>(0, 1), 0);
        put(card2, this);
        Card card3 = new Card(2, new Pair<Integer, Integer>(1, 0), 0);
        put(card3, this);
        Card card4 = new Card(3, new Pair<Integer, Integer>(1, 1), 0);
        put(card4, this);
        Card card5 = new Card(4, new Pair<Integer, Integer>(0, 0), 1);
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
        Integer img = id2img.get(card.type);
        mImageView.setImageResource(img == null ? R.drawable.ic_launcher_background : img);
        mFrameLayout.addView(mImageView);
    }

    public static int dp2px(int dp, Context context) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}