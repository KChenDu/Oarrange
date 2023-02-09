package com.example.oarrange;

import static com.example.oarrange.Utils.dp2px;
import static com.example.oarrange.Utils.generatePhase;

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
import java.util.Set;

public class GameActivity extends AppCompatActivity {
    public static Integer activeViews;
    private Integer level;
    private List<Pair<Integer, ImageView>> selections;
    private LinearLayout queue;
    private LinearLayout.LayoutParams queueParams;
    private FrameLayout mFrameLayout;

    private List<Card[][]> phase;

    public static Map<Integer, Integer> id2img = new HashMap<Integer, Integer>();

    static {
        id2img.put(0, R.drawable.orange);
        id2img.put(1, R.drawable.donut_circle);
        id2img.put(2, R.drawable.froyo_circle);
        id2img.put(3, R.drawable.ic_launcher_background);
        id2img.put(4, R.drawable.icecream_circle);
    }

    private void init() {
        selections = new ArrayList<Pair<Integer, ImageView>>();
        queue = findViewById(R.id.queue);
        queueParams = new LinearLayout.LayoutParams(dp2px(400 / 7, this), LinearLayout.LayoutParams.MATCH_PARENT);


        mFrameLayout = findViewById(R.id.board);
    }

    private void draw() {
        int layers = phase.size();
        for (int nLayer = 0; nLayer < layers; ++nLayer) {
            Card[][] layer = phase.get(nLayer);
            int rows = layer.length;
            for (int nRow = 0; nRow < rows; ++nRow) {
                Card[] row = layer[nRow];
                int cols = row.length;
                for (int nCol = 0; nCol < cols; ++nCol)
                    if (row[nCol] != null) {
                        Log.e("myLog", "put start");
                        put(row[nCol], nLayer, new Pair<Integer, Integer>(nRow, nCol), this);
                        Log.e("myLog", "put finished");
                    }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

        level = 3;
        phase = generatePhase(5, level, this);
        draw();
    }

    private void put(Card card, Integer layer, Pair<Integer, Integer> position, Context context) {
        ImageView mImageView = card.mImageView;
        mImageView.setX(dp2px(layer * 40 + position.first * 80, context));
        mImageView.setY(dp2px(layer * 40 + position.second * 80, context));
        mImageView.setZ(layer);
        Log.e("myLog", "position set");
        mImageView.setOnClickListener(v -> {
            --activeViews;
            mFrameLayout.removeView(mImageView);
            ImageView clicked = new ImageView(context);
            clicked.setLayoutParams(queueParams);
            Integer img = id2img.get(card.type);
            clicked.setImageResource(img == null ? R.drawable.ic_launcher_foreground : img);
            queue.addView(clicked);
            List<Pair<Integer, ImageView>> arrayList = new ArrayList<Pair<Integer, ImageView>>();
            Integer type = card.type;
            for (Pair<Integer, ImageView> selection : selections) {
                if (selection.first.equals(type))
                    arrayList.add(selection);
                if (arrayList.size() > 1) {
                    for (Pair<Integer, ImageView> element : arrayList) {
                        selections.remove(element);
                        queue.removeView(element.second);
                    }
                    queue.removeView(clicked);
                    if (GameActivity.activeViews < 1) {
                        mFrameLayout.removeAllViews();
                        queue.removeAllViews();
                        selections.clear();
                        if (++level > 18) {
                            finish();
                            startActivity(new Intent(context, CongratulationsActivity.class));
                        }
                        phase = generatePhase(5, level, context);
                        draw();
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
}