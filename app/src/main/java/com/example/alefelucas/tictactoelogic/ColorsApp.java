package com.example.alefelucas.tictactoelogic;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Alefe Lucas on 21/11/2015.
 */
public class ColorsApp {

    private static int bg;
    private static int fg;

    static {


        if (bg == 0) {

            bg = Color.argb(0, 0, 0, 0);
            fg = Color.argb(128, 255, 255, 255);
        }

    }

    public static int getBg() {
        return bg;
    }

    public static int getFg() {
        return fg;
    }
}
