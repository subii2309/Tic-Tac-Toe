package com.example.subin.connecter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 1;

    boolean gameIsActive = true;

    //2 means unplayed

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; // tag numbers

    public void dropIn(View view){


        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString()); //the tag number of each box


        if (gameState[tappedCounter]==2 && gameIsActive) { //When user taps on the screen

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1500f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1; //change to red
            } else {
                counter.setImageResource(R.drawable.circle);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1500f).setDuration(3000); //pull the image in



            for (int[] winningPoss : winningPos) {

                if (gameState[winningPoss[0]] == gameState[winningPoss[1]] &&
                        gameState[winningPoss[1]] == gameState[winningPoss[2]] &&
                        gameState[winningPoss[0]] != 2) {

                    gameIsActive = false;

                    String winner = "Red";
                    if(gameState[winningPoss[0]] == 0){

                        winner = "yellow";

                    }
                    //someone has won

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage) ;

                    winnerMessage.setText(winner + " has won!"); //message is set
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout); //after message is set display the layout

                    layout.setVisibility(View.VISIBLE);

                } else { //if the game is draw..if the winningPos array is not as expected.
                    boolean gameIsOver = true; //intial value
                    for (int counterState : gameState){ //check every value in gamestate

                        if(counterState == 2){ //if u find any box with value =2
                            gameIsOver =false; //if 2 is found that means there is space to click but usually it will not happen, it will not find any state ==2 so it will come out wiht gameisover value to be true and dispaly draw messgae.
                        }
                    }
                    if(gameIsOver){ //if all are true then come here and display this message
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage) ;

                        winnerMessage.setText("Its a draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }

        }
    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);




        for (int i=0;i < gameState.length; i++){

            gameState[i] = 2;
        }


        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i < gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
