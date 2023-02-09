package com.example.oarrange;

import static com.example.oarrange.GameActivity.id2img;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Utils {

    public static int dp2px(int dp, Context context) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }

    public static List<Card[][]> generatePhase(Integer nCardTypes, Integer nTriples, Context context) {
        Integer type = 0;
        LinkedList<Card> cards = new LinkedList<Card>();
        for (int i = 0 ;i < nTriples; ++i) {
            cards.add(new Card(type, context));
            cards.add(new Card(type, context));
            cards.add(new Card(type++, context));
            type %= nCardTypes;
        }
        Collections.shuffle(cards);
        List<Card[][]> phase = new ArrayList<Card[][]>();
        Set<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < 5; ++j) {
                ArrayList<Integer> triple = new ArrayList<Integer>();
                triple.add(0);
                triple.add(i);
                triple.add(j);
                set.add(triple);
            }
        Random random = new Random();
        int n = nTriples * 3;
        GameActivity.activeViews = n;
        for (int i = 0; i < n; ++i) {
            int nextInt = random.nextInt(set.size()), currentIndex = 0;
            for (ArrayList<Integer> triple : set) {
                if (currentIndex == nextInt) {
                    Integer nLayer = triple.get(0), row = triple.get(1), col = triple.get(2);
                    set.remove(triple);
                    if (nLayer >= phase.size())
                        phase.add(new Card[5 - nLayer][5 - nLayer]);
                    Card[][] layer = phase.get(nLayer);
                    Card card = cards.getFirst();
                    cards.remove();
                    layer[row][col] = card;
                    if (nLayer > 2)
                        break;
                    if (row > 0 && col > 0 && layer[row - 1][col - 1] != null && layer[row - 1][col] != null && layer[row][col - 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(nLayer + 1);
                        newTriple.add(row - 1);
                        newTriple.add(col - 1);
                        set.add(newTriple);
                    }
                    if (row > 0 && col < 4 - nLayer && layer[row - 1][col] != null && layer[row - 1][col + 1] != null && layer[row][col + 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(nLayer + 1);
                        newTriple.add(row - 1);
                        newTriple.add(col);
                        set.add(newTriple);
                    }
                    if (row < 4 - nLayer && col > 0 && layer[row][col - 1] != null && layer[row + 1][col - 1] != null && layer[row + 1][col] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(nLayer + 1);
                        newTriple.add(row);
                        newTriple.add(col - 1);
                        set.add(newTriple);
                    }
                    if (row < 4 - nLayer && col < 4 - nLayer && layer[row][col + 1] != null && layer[row + 1][col] != null && layer[row + 1][col + 1] != null) {
                        ArrayList<Integer> newTriple = new ArrayList<Integer>();
                        newTriple.add(nLayer + 1);
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

}
