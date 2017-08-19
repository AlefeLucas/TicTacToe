package com.example.alefelucas.tictactoelogic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DifficultActivity extends AppCompatActivity {

    Button buttonFacil;
    Button buttonMedio;
    Button buttonDificil;
    Button buttonImpossivel;
    SelectDifficultListener selectDifficultListener;

    private int matiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficult);

        Intent intent = getIntent();
        matiz = intent.getIntExtra("MATIZ", 180);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ColorsApp.getFg());

        }

        buttonFacil = (Button) findViewById(R.id.buttonFacil);
        buttonMedio = (Button) findViewById(R.id.buttonMedio);
        buttonDificil = (Button) findViewById(R.id.buttonDificil);
        buttonImpossivel = (Button) findViewById(R.id.buttonImpossivel);

        selectDifficultListener = new SelectDifficultListener();

        buttonFacil.setOnClickListener(selectDifficultListener);
        buttonMedio.setOnClickListener(selectDifficultListener);
        buttonDificil.setOnClickListener(selectDifficultListener);
        buttonImpossivel.setOnClickListener(selectDifficultListener);

        buttonFacil.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);
        buttonMedio.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);
        buttonDificil.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);
        buttonImpossivel.getBackground().setColorFilter(ColorsApp.getFg(), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.layout).setBackgroundColor(ColorsApp.getBg());

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
                buttonFacil.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                buttonMedio.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                buttonDificil.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
                buttonImpossivel.getBackground().setColorFilter(fg, PorterDuff.Mode.MULTIPLY);
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

    private class SelectDifficultListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            int level = -1;

            switch (id) {
                case R.id.buttonFacil:
                    level = 1;
                    break;
                case R.id.buttonMedio:
                    level = 2;
                    break;
                case R.id.buttonDificil:
                    level = 3;
                    break;
                case R.id.buttonImpossivel:
                    level = 4;
                    break;
            }

            Intent intent = new Intent(getActivity(), PlayingActivity.class);
            intent.putExtra("LEVEL", level);
            intent.putExtra("MATIZ", matiz);
            startActivity(intent);
        }
    }

    private Activity getActivity() {
        return this;
    }
}
