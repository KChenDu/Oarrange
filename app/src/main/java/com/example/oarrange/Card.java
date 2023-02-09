package com.example.oarrange;

import static com.example.oarrange.GameActivity.id2img;
import static com.example.oarrange.Utils.dp2px;

import android.content.Context;
import android.util.Pair;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Card {
    public Integer type;
    public ImageView mImageView;
    // State bool clickable

    public Card(Integer type, Context context) {
        this.type = type;
        mImageView = new ImageView(context);
        Integer img = id2img.get(type);
        mImageView.setImageResource(img == null ? R.drawable.ic_launcher_foreground : img);
        mImageView.setLayoutParams(new FrameLayout.LayoutParams(dp2px(80, context), dp2px(80, context)));
    }
}
