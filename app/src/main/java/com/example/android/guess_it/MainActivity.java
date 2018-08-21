package com.example.android.guess_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    //0 = red -----  1 = yellow
    int activePlayer = 0;

    //check whether  game is activ eor not
    boolean gameIsActive = true ;
    //gamestate where 2 is none has won yet
    int gamestate[] = {2,2,2,2,2,2,2,2,2} ;

    int winningpositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void play(View view) {

        ImageView imageview = (ImageView) view;
        int tappedCounter = Integer.parseInt(imageview.getTag().toString());

        //to check whether the box has bben filled or not
        if (gamestate[tappedCounter] == 2 && gameIsActive) {
            imageview.setTranslationY(-1000f);
            gamestate[tappedCounter] = activePlayer ;//to set gamestate has changed to yellow or red
            if (activePlayer == 0) {
                imageview.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                imageview.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            imageview.animate()
                    .translationYBy(1000f)
                    .rotation(360f)
                    .setDuration(500);
        }

        for (int winningtestposition[] : winningpositions)
        {
            if(gamestate[winningtestposition[0]]==gamestate[winningtestposition[1]]&&gamestate[winningtestposition[1]]==gamestate[winningtestposition[2]]&&gamestate[winningtestposition[1]]!=2)
            {
                //Someone has one
                gameIsActive = false ;

                String winner = "Red " ;

                if(gamestate[winningtestposition[0]]==1)
                   winner = "Yellow " ;


                TextView playwinner = findViewById(R.id.winner);
                playwinner.setText(winner+"player has won :)");

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                linearLayout.setVisibility(View.VISIBLE);
            }
            //For Draw cases
            else
            {
                boolean gameIsOver = true;
                for(int counterstate : gamestate)
                  {
                    if(counterstate == 2)
                        gameIsOver = false ;
                  }
                 if(gameIsOver){
                        TextView playwinner = findViewById(R.id.winner);
                        playwinner.setText("It's a draw :|");

                        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }

            }
         }

    }

    public void playAgain(View view)
    {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        linearLayout.setVisibility(View.INVISIBLE);

        gameIsActive = true ;
        activePlayer = 0 ;

        for(int i=0;i<gamestate.length;i++)
            gamestate[i]=2;
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
