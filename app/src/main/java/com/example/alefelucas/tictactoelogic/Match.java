package com.example.alefelucas.tictactoelogic;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alefe Lucas on 20/11/2015.
 */
public class Match {

    private Slot[][] board = new Slot[3][3];
    private Won[][] boardWon = new Won[3][3];
    private ImageButton[][] boardButtons;
    private boolean turnX;
    private boolean xStarts;
    private BoardButtonsListener boardButtonsListener;
    private Context context;
    private TextView statusTV;
    private TextView xTV;
    private TextView oTV;
    private Button buttonRestart;
    private TextView tip;

    private int draws;
    private int xWons;
    private int oWons;


    private int level;

    public ImageButton[][] getBoardButtons() {
        return boardButtons;
    }

    public boolean isTurnX() {
        return turnX;
    }

    public Match(Context context, ImageButton[][] boardButtons, boolean xStarts, TextView statusTV, TextView xTV, TextView oTV, Button buttonRestart, int level, TextView tip) {
        this.tip = tip;
        this.context = context;
        this.statusTV = statusTV;
        this.boardButtons = boardButtons;
        this.turnX = xStarts;
        this.xStarts = xStarts;
        this.xTV = xTV;
        this.oTV = oTV;
        this.buttonRestart = buttonRestart;
        this.level = level;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = Slot.EMPTY;
                boardWon[x][y] = Won.FG;
            }
        }


        boardButtonsListener = new BoardButtonsListener();

        for (ImageButton[] buttonLines : boardButtons) {
            for (ImageButton button : buttonLines) {
                button.setOnClickListener(boardButtonsListener);
            }
        }

        if (!turnX && level != 0) {
            aiPlays();
        }
    }


    public void find3() {
        //imageButton.setBackgroundResource(R.color.bg));


        for (int x = 0; x < board.length; x++) {
            if (board[0][x] == board[1][x] && board[1][x] == board[2][x] && board[0][x] != Slot.EMPTY ) {
                boardButtons[0][x].setBackgroundColor(ColorsApp.getBg());
                boardWon[0][x] = Won.BG;
                boardButtons[1][x].setBackgroundColor(ColorsApp.getBg());
                boardWon[1][x] = Won.BG;
                boardButtons[2][x].setBackgroundColor(ColorsApp.getBg());
                boardWon[2][x] = Won.BG;
            }

            if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][0] != Slot.EMPTY) {
                boardButtons[x][0].setBackgroundColor(ColorsApp.getBg());
                boardWon[x][0] = Won.BG;
                boardButtons[x][1].setBackgroundColor(ColorsApp.getBg());
                boardWon[x][1] = Won.BG;
                boardButtons[x][2].setBackgroundColor(ColorsApp.getBg());
                boardWon[x][2] = Won.BG;
            }

        }

        if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] != Slot.EMPTY){
            boardButtons[0][2].setBackgroundColor(ColorsApp.getBg());
            boardWon[0][2] = Won.BG;
            boardButtons[1][1].setBackgroundColor(ColorsApp.getBg());
            boardWon[1][1] = Won.BG;
            boardButtons[2][0].setBackgroundColor(ColorsApp.getBg());
            boardWon[2][0] = Won.BG;
        }

        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] != Slot.EMPTY){
            boardButtons[0][0].setBackgroundColor(ColorsApp.getBg());
            boardWon[0][0] = Won.BG;
            boardButtons[1][1].setBackgroundColor(ColorsApp.getBg());
            boardWon[1][1] = Won.BG;
            boardButtons[2][2].setBackgroundColor(ColorsApp.getBg());
            boardWon[2][2] = Won.BG;
        }
    }

    public Won[][] getBoardWon() {
        return boardWon;
    }

    public void restart() {


        buttonRestart.setVisibility(View.GONE);
        tip.setVisibility(View.VISIBLE);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = Slot.EMPTY;
                boardWon[x][y] = Won.FG;
                boardButtons[x][y].setEnabled(true);
                boardButtons[x][y].setImageResource(android.R.color.transparent);
                boardButtons[x][y].setBackgroundColor(ColorsApp.getFg());
            }
        }

        xStarts = !xStarts;
        turnX = xStarts;

        if (!turnX && level != 0) {
            aiPlays();
        }
    }

    public int getRowByButtonId(int buttonId) {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int column = 0; column < boardButtons[row].length; column++) {
                if (boardButtons[row][column].getId() == buttonId) {
                    return row;
                }
            }
        }

        return -1;
    }

    public int getColumnByButtonId(int buttonId) {
        for (int row = 0; row < boardButtons.length; row++) {
            for (int column = 0; column < boardButtons[row].length; column++) {
                if (boardButtons[row][column].getId() == buttonId) {
                    return column;
                }
            }
        }

        return -1;
    }

    public BoardStatus place(int row, int column) {
        System.out.println(getStatus().toString());

        if (getStatus() == BoardStatus.RUNNING) {


            if (!slotIsEmpty(row, column)) {
                System.out.println("Slot (" + row + "," + column + ") ocupado.");
            }
            board[row][column] = (turnX ? Slot.X : Slot.O);
            boardButtons[row][column].setImageResource((turnX ? R.drawable.x : R.drawable.o));
            boardButtons[row][column].setEnabled(false);
            turnX = !turnX;

            if (getStatus() == BoardStatus.RUNNING) {

            } else if (getStatus() == BoardStatus.DRAW) {

                draws++;
            } else if (getStatus() == BoardStatus.X_WON) {

                xWons++;
            } else if (getStatus() == BoardStatus.O_WON) {

                oWons++;
            }

            if (getStatus() == BoardStatus.O_WON || getStatus() == BoardStatus.X_WON) {
                find3();
            }

            if (getStatus() != BoardStatus.RUNNING) {
                buttonRestart.setVisibility(View.VISIBLE);
                tip.setVisibility(View.GONE);
            }
        }

        statusTV.setText("Empates: " + draws);
        oTV.setText("O: " + oWons);
        xTV.setText("X: " + xWons);

        return getStatus();
    }


    public BoardStatus place(ImageButton button) {
        int id = button.getId();

        int row = this.getRowByButtonId(id);
        int column = this.getColumnByButtonId(id);

        return this.place(row, column);


    }

    public boolean slotIsEmpty(int row, int column) {
        System.out.printf("Row: %d, Column : %d%n", row, column);
        if (board[row][column] == Slot.EMPTY) {
            return true;
        }
        return false;
    }

    public BoardStatus getStatus() {
        BoardStatus status = null;

        for (int x = 0; x < board.length; x++) {
            //horizontal
            if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][0] != Slot.EMPTY) {
                if (board[x][0] == Slot.X) {
                    status = BoardStatus.X_WON;

                } else {
                    status = BoardStatus.O_WON;
                }

            } //vertical
            else if (board[0][x] == board[1][x] && board[1][x] == board[2][x] && board[0][x] != Slot.EMPTY) {
                if (board[0][x] == Slot.X) {
                    status = BoardStatus.X_WON;
                } else {
                    status = BoardStatus.O_WON;
                }

            }
        }

        if (status != null) {

            return status;
        }

        int x = 0;
        //diagonal
        if (board[x][x] == board[x + 1][x + 1] && board[x + 1][x + 1] == board[x + 2][x + 2] && board[x][x] != Slot.EMPTY) {
            if (board[x][x] == Slot.X) {
                status = BoardStatus.X_WON;
            } else {
                status = BoardStatus.O_WON;
            }

        } else if (board[x][x + 2] == board[x + 1][x + 1] && board[x + 1][x + 1] == board[x + 2][x] && board[x][x + 2] != Slot.EMPTY) {
            if (board[x][x + 2] == Slot.X) {
                status = BoardStatus.X_WON;
            } else {
                status = BoardStatus.O_WON;
            }

        }


        if (status != null) {

            return status;
        }

        //Adiciona os slots do tabuleiro a uma ArrayList de slots e verifica se essa ArrayList contém slot vazio
        ArrayList<Slot> slots = new ArrayList<Slot>();
        for (Slot rows[] : board) {
            for (Slot slot : rows) {
                slots.add(slot);
            }
        }
        if (slots.contains(Slot.EMPTY)) {
            status = BoardStatus.RUNNING;

        } //caso não contenha, é empate
        else {
            status = BoardStatus.DRAW;

        }

        return status;
    }


    private class BoardButtonsListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (tip.getText().toString().equals("SUA VEZ")) {
                ImageButton button = (ImageButton) v;
                System.out.println("Você jogando");
                place(button);


                if (!turnX && level != 0 && getStatus() == BoardStatus.RUNNING) {
                    tip.setText("ANDROID JOGANDO...");
                    new CountDownTimer(250, 250) {

                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            aiPlays();
                            tip.setText("SUA VEZ");
                        }
                    }.start();

                }
            }
        }
    }

    public void aiPlays() {


        int[] places = ComputerAI.play(level, board);
        place(places[0], places[1]);

    }

}
