package edu.wiu.krl102.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static android.view.View.VISIBLE;
import static android.view.View.INVISIBLE;
import static edu.wiu.krl102.tictactoe.R.*;


public class Game extends ActionBarActivity{

    private boolean PlayerTurn = true; //
    private boolean WinStatus = false;
    private int winner=0;
    private int board[][] = new int[3][3]; // for now we will represent the board as an array of ints

    //MainActivity main = new MainActivity();
    boolean singlePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_game);
        //Passes in boolean value from MainActivity button
        singlePlayer = getIntent().getBooleanExtra("singlePlayer", true);


        ///////////////TESTING////////////////////
        //  Used for testing to see value stored in board
        //String a = Integer.toString(board[0][0]);
        //String b = "board[0][0] = ";
        //Toast.makeText(this, b + a, Toast.LENGTH_LONG).show();

        //   Used to make sure correct boolean is passed through when selecting "single player" or "2 players"
        //if(singlePlayer)
        //    Toast.makeText(this, "singlePlayer", Toast.LENGTH_LONG).show();
        //else
        //    Toast.makeText(this, "2Player", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void assignImage(ImageView v){
        if(PlayerTurn)
            v.setImageResource(R.drawable.x);
        else
            v.setImageResource(R.drawable.circle);
        v.setClickable(false);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case id.btn1:
                if(PlayerTurn)
                    board[0][0] = 1;
                else
                    board[0][0] = 2;
                assignImage((ImageView) v);
                //Used for testing to see value stored in board
                       // String a = Integer.toString(board[0][0]);
                       // String b = "board[0][0] = ";
                       // Toast.makeText(this, b + a, Toast.LENGTH_LONG).show();
                break;
            case id.btn2:
                if(PlayerTurn)
                    board[0][1] = 1;
                else
                    board[0][1] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn3:
                if(PlayerTurn)
                    board[0][2] = 1;
                else
                   board[0][2] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn4:
                if(PlayerTurn)
                    board[1][0] = 1;
                else
                    board[1][0] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn5:
                if(PlayerTurn)
                    board[1][1] = 1;
                else
                    board[1][1] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn6:
                if(PlayerTurn)
                    board[1][2] = 1;
                else
                    board[1][2] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn7:
                if(PlayerTurn)
                    board[2][0] = 1;
                else
                    board[2][0] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn8:
                if(PlayerTurn)
                    board[2][1] = 1;
                else
                    board[2][1] = 2;
                assignImage((ImageView) v);
                break;
            case id.btn9:
                if(PlayerTurn)
                    board[2][2] = 1;
                else
                    board[2][2] = 2;
                assignImage((ImageView) v);
                break;

        }
        checkWin();
        if(!WinStatus)
            checkDraw();
        if(WinStatus) {
            setUnclickable();
            doEndGame();
        }
        if(PlayerTurn) {
            PlayerTurn = false;
            if (singlePlayer) {
                aiTurn();
            }
        }else
            PlayerTurn=true;

    }

    public void doEndGame(){
        String winString=null;
        if(winner==1)
            if(singlePlayer)
                winString="Player wins";
            else
                winString="Player-X wins";
        if(winner==2)
            if(singlePlayer)
                winString="Computer wins";
            else
                winString="Player-O wins";
        if(winner==3)
            winString="Draw";
        Toast.makeText(this, winString, Toast.LENGTH_LONG).show();
        Button btn = (Button) findViewById(id.button3);
        btn.setVisibility(VISIBLE);
    }

    public void onRestart(View v) {
        PlayerTurn = true;
        WinStatus = false;
        winner=0;
        Button btn = (Button) findViewById(id.button3);
        btn.setVisibility(INVISIBLE);

        ImageView a1 = (ImageView) findViewById(id.btn1);
        a1.setImageResource(R.drawable.blank);
        a1.setClickable(true);

        ImageView a2 = (ImageView) findViewById(id.btn2);
        a2.setImageResource(R.drawable.blank);
        a2.setClickable(true);

        ImageView a3 = (ImageView) findViewById(id.btn3);
        a3.setImageResource(R.drawable.blank);
        a3.setClickable(true);

        ImageView a4 = (ImageView) findViewById(id.btn4);
        a4.setImageResource(R.drawable.blank);
        a4.setClickable(true);

        ImageView a5 = (ImageView) findViewById(id.btn5);
        a5.setImageResource(R.drawable.blank);
        a5.setClickable(true);

        ImageView a6 = (ImageView) findViewById(id.btn6);
        a6.setImageResource(R.drawable.blank);
        a6.setClickable(true);

        ImageView a7 = (ImageView) findViewById(id.btn7);
        a7.setImageResource(R.drawable.blank);
        a7.setClickable(true);

        ImageView a8 = (ImageView) findViewById(id.btn8);
        a8.setImageResource(R.drawable.blank);
        a8.setClickable(true);

        ImageView a9 = (ImageView) findViewById(id.btn9);
        a9.setImageResource(R.drawable.blank);
        a9.setClickable(true);

        //Clears game data for checkWin
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }

    }

    public void setUnclickable(){
        ImageView a1 = (ImageView) findViewById(id.btn1);
        a1.setClickable(false);

        ImageView a2 = (ImageView) findViewById(id.btn2);
        a2.setClickable(false);

        ImageView a3 = (ImageView) findViewById(id.btn3);
        a3.setClickable(false);

        ImageView a4 = (ImageView) findViewById(id.btn4);
        a4.setClickable(false);

        ImageView a5 = (ImageView) findViewById(id.btn5);
        a5.setClickable(false);

        ImageView a6 = (ImageView) findViewById(id.btn6);
        a6.setClickable(false);

        ImageView a7 = (ImageView) findViewById(id.btn7);
        a7.setClickable(false);

        ImageView a8 = (ImageView) findViewById(id.btn8);
        a8.setClickable(false);

        ImageView a9 = (ImageView) findViewById(id.btn9);
        a9.setClickable(false);
    }

    public void onBackClick(View v){
        onBackPressed();
    }

    public void checkWin(){

        if( (board[0][0]==board[0][1] && board[0][0]==board[0][2] && board[0][0]!=0) || //row1 win
            (board[0][0]==board[1][0] && board[0][0]==board[2][0] && board[0][0]!=0) || //col1 win
            (board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]!=0))  //Left top to bottom right diagonal win
            {
                winner = board[0][0];
                WinStatus=true;

            }
        if( (board[2][0]==board[2][1] && board[2][0]==board[2][2] && board[2][0]!=0) || //row3 win
            (board[0][2]==board[1][2] && board[0][2]==board[2][2] && board[0][2]!=0)) //col3 win
            {
                winner = board[2][2];
                WinStatus=true;

            }

        if( (board[1][0]==board[1][1] && board[1][0]==board[1][2] && board[1][0]!=0) || //row2 win
            (board[0][1]==board[1][1] && board[0][1]==board[2][1] && board[0][1]!=0) || //col2 win
            (board[0][2]==board[1][1] && board[0][2]==board[2][0] && board[0][2]!=0))  //Right top to bottom left diagonal win
            {
                winner = board[1][1];
                WinStatus=true;

            }

    }

    public void checkDraw(){
        //Checks to see if all boxes have been marked to signal a draw
        if( (board[0][0]!=0 && board[0][1]!=0 && board[0][2]!=0) &&
                (board[1][0]!=0 && board[1][1]!=0 && board[1][2]!=0) &&
                (board[2][0]!=0 && board[2][1]!=0 && board[2][2]!=0))
        {
            winner = 3;
            WinStatus=true;
        }

    }

    public void aiTurn(){
        //Toast.makeText(this, "aiTurn", Toast.LENGTH_LONG).show();
        ImageView view = null;
        Random rand = new Random();
        boolean findOpenSlot=true;
        while(findOpenSlot) {
            int rn = rand.nextInt(9 + 1);

            if (rn == 1 && board[0][0] == 0){
                view = (ImageView) findViewById(id.btn1);
                findOpenSlot=false;
            }
            if (rn == 2 && board[0][1] == 0){
                view = (ImageView) findViewById(id.btn2);
                findOpenSlot=false;
            }
            if (rn == 3 && board[0][2] == 0){
                view = (ImageView) findViewById(id.btn3);
                findOpenSlot=false;
            }
            if (rn == 4 && board[1][0] == 0){
                view = (ImageView) findViewById(id.btn4);
                findOpenSlot=false;
            }
            if (rn == 5 && board[1][1] == 0){
                view = (ImageView) findViewById(id.btn5);
                findOpenSlot=false;
            }
            if (rn == 6 && board[1][2] == 0){
                view = (ImageView) findViewById(id.btn6);
                findOpenSlot=false;
            }
            if (rn == 7 && board[2][0] == 0){
                view = (ImageView) findViewById(id.btn7);
                findOpenSlot=false;
            }
            if (rn == 8 && board[2][1] == 0){
                view = (ImageView) findViewById(id.btn8);
                findOpenSlot=false;
            }
            if (rn == 9 && board[2][2] == 0){
                view = (ImageView) findViewById(id.btn9);
                findOpenSlot=false;
            }

        }
        onClick(view);
    }
}


