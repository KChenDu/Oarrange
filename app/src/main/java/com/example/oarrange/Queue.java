package com.example.oarrange;

import static com.example.oarrange.GameActivity.id2img;
import static com.example.oarrange.Utils.dp2px;

import android.content.Context;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class Queue {
    private List<Pair<Integer, ImageView>> selections;
    private LinearLayout.LayoutParams queueParams;

    public Queue(Context context) {
        queueParams = new LinearLayout.LayoutParams(dp2px(400 / 7, context), LinearLayout.LayoutParams.MATCH_PARENT);
        //this.type = type;
        //mImageView = new ImageView(context);
        //Integer img = id2img.get(type);
        //mImageView.setImageResource(img == null ? R.drawable.ic_launcher_foreground : img);
        //mImageView.setLayoutParams(new FrameLayout.LayoutParams(dp2px(80, context), dp2px(80, context)));
    }
}
