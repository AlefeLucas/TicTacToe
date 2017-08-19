package com.example.alefelucas.tictactoelogic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button buttonOnePlayer;
    private Button buttonTwoPlayers;

    private int matiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        buttonOnePlayer = (Button) findViewById(R.id.buttonOnePlayer);
        buttonTwoPlayers = (Button) findViewById(R.id.buttonTwoPlayers);

        buttonTwoPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlayingActivity.class);
                intent.putExtra("MATIZ", matiz);
                startActivity(intent);
            }
        });

        buttonOnePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DifficultActivity.class);
                intent.putExtra("MATIZ", matiz);
                startActivity(intent);
            }
        });

        findViewById(R.id.layout).setBackgroundColor(ColorsApp.getBg());

        buttonOnePlayer.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);
        buttonTwoPlayers.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ColorsApp.getFg());

        }

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
                buttonOnePlayer.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                buttonTwoPlayers.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(fg);
                }

                matiz++;
                this.start();
            }
        }.start();






    }



    private Activity getActivity (){
        return this;
    }
}
