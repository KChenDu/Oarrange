package com.example.oarrange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyCallback;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Integer level, activeViews;
    private ArrayList<Pair<Integer, ImageView>> selections;
    private LinearLayout queue;
    private LinearLayout.LayoutParams queueParams;
    private FrameLayout mFrameLayout;
    private FrameLayout.LayoutParams mFrameLayoutParams;
    private HashMap<Integer, Integer> id2img;

    private ArrayList<Integer[][]> generatePhase(Integer nCardTypes, Integer n_triples, Context context) {
        activeViews = n_triples * 3;
        Integer type = 0;
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for (int i = 0 ;i < n_triples; ++i) {
            cards.add(type);
            cards.add(type);
            cards.add(type++);
            type %= nCardTypes;
        }
        Collections.shuffle(cards);
        ArrayList<Integer[][]> phase = new ArrayList<Integer[][]>();
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j) {
                ArrayList<Integer> triple = new ArrayList<Integer>();
                triple.add(0);
                triple.add(i);
                triple.add(j);
                set.add(triple);
            }
        Random random = new Random();
        int n = n_triples * 3;
        for (int i = 0; i < n; ++i) {
            int nextInt = random.nextInt(set.size()), currentIndex = 0;
            for (ArrayList<Integer> triple : set) {
                if (currentIndex == nextInt) {
                    Integer n_layer = triple.get(0), row = triple.get(1), col = triple.get(2);
                    if (n_layer >= phase.size())
                        phase.add(new Integer[5 - n_layer][5 - n_layer]);
                    Integer[][] layer = phase.get(n_layer);
                    type = cards.get(0);
                    cards.remove(0);
                    layer[row][col] = type;
                    set.remove(triple);
                    put(new Card(type, new Pair<Integer, Integer>(row, col), n_layer), context);
                    if (n_layer > 2)
                        break;
                    if (row > 0 && col > 0 && layer[row - 1][col - 1] != null && layer[row - 1][col] != null && layer[row][col - 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(n_layer + 1);
                        newTriple.add(row - 1);
                        newTriple.add(col - 1);
                        set.add(newTriple);
                    }
                    if (row > 0 && col < 4 - n_layer && layer[row - 1][col] != null && layer[row - 1][col + 1] != null && layer[row][col + 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(n_layer + 1);
                        newTriple.add(row - 1);
                        newTriple.add(col);
                        set.add(newTriple);
                    }
                    if (row < 4 - n_layer && col > 0 && layer[row][col - 1] != null && layer[row + 1][col - 1] != null && layer[row + 1][col] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(n_layer + 1);
                        newTriple.add(row);
                        newTriple.add(col - 1);
                        set.add(newTriple);
                    }
                    if (row < 4 - n_layer && col < 4 - n_layer && layer[row][col + 1] != null && layer[row + 1][col] != null && layer[row + 1][col + 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(n_layer + 1);
                        newTriple.add(row);
                        newTriple.add(col);
                        set.add(newTriple);
                    }
                    break;
                }
                ++currentIndex;
            }
        }
        return phase;
    }

    private void init() {
        selections = new ArrayList<Pair<Integer, ImageView>>();
        queue = findViewById(R.id.queue);
        queueParams = new LinearLayout.LayoutParams(dp2px(400 / 7, this), LinearLayout.LayoutParams.MATCH_PARENT);
        mFrameLayout = findViewById(R.id.board);
        mFrameLayoutParams = new FrameLayout.LayoutParams(dp2px(80, this), dp2px(80, this));
        id2img = new HashMap<Integer, Integer>();
        id2img.put(0, R.drawable.orange);
        id2img.put(1, R.drawable.donut_circle);
        id2img.put(2, R.drawable.froyo_circle);
        id2img.put(3, R.drawable.ic_launcher_background);
        id2img.put(4, R.drawable.icecream_circle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

        level = 18;
        generatePhase(5, level, this);
    }

    private static class Card {
        public Integer type;
        public Pair<Integer, Integer> position;
        public Integer layer;
        // State bool clickable

        public Card(Integer type, Pair<Integer, Integer> position, Integer layer) {
            this.type = type;
            this.position = position;
            this.layer = layer;
        }
    }

    private void put(Card card, Context context) {
        ImageView mImageView = new ImageView(context);
        mImageView.setLayoutParams(mFrameLayoutParams);
        Integer layer = card.layer;
        mImageView.setX(dp2px(layer * 40 + card.position.first * 80, this));
        mImageView.setY(dp2px(layer * 40 + card.position.second * 80, this));
        mImageView.setZ(card.layer);
        Integer type = card.type, img = id2img.get(type);
        mImageView.setImageResource(img == null ? R.drawable.ic_launcher_background : img);
        mImageView.setOnClickListener(v -> {
            --activeViews;
            // mImageView.setVisibility(View.GONE);
            mFrameLayout.removeView(mImageView);
            ImageView clicked = new ImageView(context);
            clicked.setLayoutParams(queueParams);
            clicked.setImageResource(img == null ? R.drawable.ic_launcher_background : img);
            queue.addView(clicked);
            ArrayList<Pair<Integer, ImageView>> arrayList = new ArrayList<Pair<Integer, ImageView>>();
            for (Pair<Integer, ImageView> selection : selections) {
                if (selection.first.equals(type))
                    arrayList.add(selection);
                if (arrayList.size() > 1) {
                    for (Pair<Integer, ImageView> element : arrayList) {
                        selections.remove(element);
                        queue.removeView(element.second);
                    }
                    queue.removeView(clicked);
                    if (activeViews < 1) {
                        mFrameLayout.removeAllViews();
                        queue.removeAllViews();
                        selections.clear();
                        if (++level > 18) {
                            finish();
                            startActivity(new Intent(context, CongratulationsActivity.class));
                        }
                        generatePhase(5, level, context);
                    }
                    return;
                }
            }
            if (selections.size() > 5) {
                finish();
                startActivity(new Intent(context, GameOverActivity.class));
            }
            selections.add(new Pair<Integer, ImageView>(type, clicked));
        });
        mFrameLayout.addView(mImageView);
    }

    public static int dp2px(int dp, Context context) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}