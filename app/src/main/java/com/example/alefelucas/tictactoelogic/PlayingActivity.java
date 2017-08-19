package com.example.alefelucas.tictactoelogic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class PlayingActivity extends AppCompatActivity {

    private Button buttonRestart;
    private TextView textViewTurn;
    private TextView textViewTip;
    private TextView textViewX;
    private TextView textViewO;

    private int matiz;

    private Match match;

    private ImageButton[][] buttonsBoard;

    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        tableLayout.getLayoutParams().width = width;
        tableLayout.getLayoutParams().height = width;

        buttonRestart = (Button) findViewById(R.id.buttonRestart);
        textViewTurn = (TextView) findViewById(R.id.textViewTurn);
        textViewO = (TextView) findViewById(R.id.textViewO);
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewTip = (TextView) findViewById(R.id.textViewTip);

        buttonsBoard = new ImageButton[3][3];
        findViewById(R.id.layout).setBackgroundColor(ColorsApp.getBg());
        buttonsBoard[0][0] = (ImageButton) findViewById(R.id.button00);
        buttonsBoard[0][1] = (ImageButton) findViewById(R.id.button01);
        buttonsBoard[0][2] = (ImageButton) findViewById(R.id.button02);
        buttonsBoard[1][0] = (ImageButton) findViewById(R.id.button10);
        buttonsBoard[1][1] = (ImageButton) findViewById(R.id.button11);
        buttonsBoard[1][2] = (ImageButton) findViewById(R.id.button12);
        buttonsBoard[2][0] = (ImageButton) findViewById(R.id.button20);
        buttonsBoard[2][1] = (ImageButton) findViewById(R.id.button21);
        buttonsBoard[2][2] = (ImageButton) findViewById(R.id.button22);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ColorsApp.getFg());

        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.color.color1);
        colors.add(R.color.color2);
        colors.add(R.color.color3);
        colors.add(R.color.color4);
        colors.add(R.color.color5);
        colors.add(R.color.color6);
        colors.add(R.color.color7);
        colors.add(R.color.color8);
        colors.add(R.color.color9);

        Random random = new Random();
        int x = 0;

        for (ImageButton[] bLine : buttonsBoard) {
            int y = 0;
            for (ImageButton imageButton : bLine) {
                imageButton.getLayoutParams().width = (tableLayout.getLayoutParams().width / 3) - 3;
                imageButton.getLayoutParams().height = (tableLayout.getLayoutParams().height / 3) - 3;
                imageButton.setBackgroundColor(ColorsApp.getFg());
                LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) imageButton.getLayoutParams();

                int bottom = 0;
                int top = 0;

                if (x == 0) {
                    bottom = 3;
                } else if (x == 2) {
                    top = 3;
                }

                int left = 0;
                int right = 0;
                if (y == 0) {
                    right = 3;
                } else if (y == 2) {
                    left = 3;
                }

                layout.setMargins(left, top, right, bottom);
                y++;
            }
            x++;
        }

        Intent intent = getIntent();
        int level = intent.getIntExtra("LEVEL", 0);
        matiz = intent.getIntExtra("MATIZ", 180);

        match = new Match(this, buttonsBoard, new Random().nextBoolean(), textViewTurn, textViewX, textViewO, buttonRestart, level, textViewTip);


        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match.restart();
            }
        });
        buttonRestart.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);

        float saturacao = 0.75F;
        float valor = 0.3F;

        //int fg = Color.HSVToColor(new float[]{matiz, saturacao, valor + 0.3F});



        new CountDownTimer(10, 10) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                matiz = (matiz >= 360 ? 0 : matiz);
                float saturacao = 0.75F;
                float valor = 0.3F;

                int bg = Color.HSVToColor(new float[]{matiz, saturacao, valor});
                int fg = Color.HSVToColor(new float[]{matiz, saturacao, valor + 0.3F});

                findViewById(R.id.layout).setBackgroundColor(bg);

                for (int x = 0; x < buttonsBoard.length; x++) {
                    for (int y = 0; y < buttonsBoard[x].length; y++) {
                        Won [][] won  = match.getBoardWon();
                        if(won[x][y] == Won.BG){
                            buttonsBoard[x][y].setBackgroundColor(bg);
                        } else{
                            buttonsBoard[x][y].setBackgroundColor(fg);
                        }

                    }
                }

                buttonRestart.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(fg);
                }

                matiz++;
                this.start();
            }
        }.start();
    }


    private Activity getActivity() {
        return this;
    }
}
