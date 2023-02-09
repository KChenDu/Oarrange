package com.example.oarrange;

import android.util.Pair;

public class Card {
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
