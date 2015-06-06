package com.ithinkbest.myapplication5;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import com.ithinkbest.app5.b5util.*;
import com.ithinkbest.myapplication5.b5util.B253;
import com.ithinkbest.myapplication5.b5util.Board5x5;
import com.ithinkbest.myapplication5.b5util.Board5x5Counter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import b5util.B253;
//import b5util.Board5x5;
//import b5util.Board5x5Counter;


//300#E0E0E0
//        400#BDBDBD
//        500#9E9E9E
//        600#757575


//public class BtnCntLinesActivity extends Activity implements Button.OnClickListener {


//public class GamePlayActivity extends ActionBarActivity implements Button.OnClickListener {
    public class Game1Fragment extends Fragment implements Button.OnClickListener {


        public static final String TAG = "GAME1";


    static int textBoardStatus;
    static int game_mode = 1;
    static final int TEXT_BOARD_STATUS_CHOOSE_SET_SHOW = -1;
    static final int TEXT_BOARD_STATUS_CHOOSE_SET_HIDE = -2;

    static final int TEXT_BOARD_STATUS_1 = 1;
    static final int TEXT_BOARD_STATUS_2 = 2;
    static final int TEXT_BOARD_STATUS_3 = 3;

    android.support.v7.widget.CardView btnStart;
    android.support.v7.widget.CardView btnReset;
    android.support.v7.widget.CardView cvTextBoard;

    TextView tvTitle;

    Random rnd;
    boolean isGameOver;
    boolean isChoosingSet;

    String gameOverMsg;
    private B253 b1;//Human
    private B253 b2;//Android

    private int color300 = 0xFFE0E0E0;
    private int color400 = 0xFFBDBDBD;
    private int color500 = 0xFF9E9E9E;
    private int color600 = 0xFF757575;

    private int[] valArr1;
    private int[] valArr2;
    private static String LOG_TAG = "MARK987";
    private TextView txtStatus;
    private TextView txtDebug1;
    private TextView txtDebug2;


    private List<Button> buttons;
    private static int whoseTurn = 0;
    private static final int humanTurn = 0;
    private static final int androidTurn = 1;

    private List<Integer> checkedList1; // human
    private List<Integer> checkedList2; // android

    private static final int[] BUTTON_IDS = {
            R.id.btn0,
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5,
            R.id.btn6,
            R.id.btn7,
            R.id.btn8,
            R.id.btn9,
            R.id.btn10,
            R.id.btn11,
            R.id.btn12,
            R.id.btn13,
            R.id.btn14,
            R.id.btn15,
            R.id.btn16,
            R.id.btn17,
            R.id.btn18,
            R.id.btn19,
            R.id.btn20,
            R.id.btn21,
            R.id.btn22,
            R.id.btn23,
            R.id.btn24,
    };


    Toolbar b5Toolbar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        b5Toolbar =(Toolbar)findViewById(R.id.b5_toolbar);
//        setSupportActionBar(b5Toolbar);
//    }
public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String GAME_TYPE1 = "GAME_TYPE1";
    public static final String GAME_TYPE2 = "GAME_TYPE2";
    public static final String GAME_TYPE3 = "GAME_TYPE3";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_game1, container, false);

        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        if (GameAbout.mode==1){
            tvTitle.setText("Human vs Android");
            game_mode = 1;
        }
        if (GameAbout.mode==2){
            tvTitle.setText("Android vs Human");
            game_mode = 2;
        }


        btnStart = (CardView) v.findViewById(R.id.btnStart);
        btnReset = (CardView) v.findViewById(R.id.btnReset);
        cvTextBoard = (CardView) v.findViewById(R.id.cv_textboard);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (game_mode) {
                    case 1: // human vs android
                        isChoosingSet = false;
                        btnStart.setVisibility(View.GONE);
                        break;
                    case 2: //android vs human
                        isChoosingSet = false;
                        whoseTurn = androidTurn;
                        androidPlay();
                        showTextBoard();
                        textBoardStatus = TEXT_BOARD_STATUS_2;
                        btnStart.setVisibility(View.GONE);

                        break;
                    case 3:
                        //isChoosingSet=false;
                        break;


                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
        cvTextBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTextBoardStatus();
                showTextBoard();            }
        });


//        Intent intent = getIntent();
        //    String message = intent.getStringExtra(GameMenuActivity.EXTRA_MESSAGE);
//        String message = intent.getStringExtra(EXTRA_MESSAGE);
//
//        b5Toolbar = (Toolbar) v.findViewById(R.id.b5_toolbar);
//        setSupportActionBar(b5Toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        isChoosingSet = true;
//        if (message.equals(GAME_TYPE1)) {
//            String str = getString(R.string.item1_title);
//            Log.d(LOG_TAG, "...str=" + str);
//            // b5Toolbar.setTitle(str); // NOT WORKING
//            game_mode = 1;
//            getSupportActionBar().setTitle(str);
//        }
//        if (message.equals(GAME_TYPE2)) {
//            String str = getString(R.string.item2_title);
//            Log.d(LOG_TAG, "...str=" + str);
//            // b5Toolbar.setTitle(str); // NOT WORKING
//            game_mode = 2;
//            getSupportActionBar().setTitle(str);
//        }
//        if (message.equals(GameMenuActivity.GAME_TYPE3)) {
//            String str = getString(R.string.item3_title);
//            Log.d(LOG_TAG, "...str=" + str);
//            // b5Toolbar.setTitle(str); // NOT WORKING
//            game_mode = 3;
//
//            getSupportActionBar().setTitle(str);
//        }


        txtStatus = (TextView) v.findViewById(R.id.txtStatus);
        txtDebug1 = (TextView) v.findViewById(R.id.txtDebug1);
        txtDebug2 = (TextView) v.findViewById(R.id.txtDebug2);

        txtDebug1.setBackgroundColor(color400);
        txtDebug2.setBackgroundColor(color300);


        Random rnd = new Random();
        checkedList1 = new ArrayList<>();
        buttons = new ArrayList<Button>();
        for (int id : BUTTON_IDS) {
            Button button = (Button) v.findViewById(id);
            button.setOnClickListener(this); // maybe
            button.setTextAppearance(getActivity(), android.R.style.TextAppearance_Large);
            buttons.add(button);
        }
        for (int i = 0; i < 25; i++) {
            buttons.get(i).setText("" + i);
        }
        resetBtnBackground();

        resetGame();
        return v;
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        b5Toolbar = (Toolbar) findViewById(R.id.b5_toolbar);
//        setSupportActionBar(b5Toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        isChoosingSet = true;
//        if (message.equals(GAME_TYPE1)) {
//            String str = getString(R.string.item1_title);
//            Log.d(LOG_TAG, "...str=" + str);
//            // b5Toolbar.setTitle(str); // NOT WORKING
//            game_mode = 1;
//            getSupportActionBar().setTitle(str);
//        }
//        if (message.equals(GAME_TYPE2)) {
//            String str = getString(R.string.item2_title);
//            Log.d(LOG_TAG, "...str=" + str);
//            // b5Toolbar.setTitle(str); // NOT WORKING
//            game_mode = 2;
//            getSupportActionBar().setTitle(str);
//        }
////        if (message.equals(GameMenuActivity.GAME_TYPE3)) {
////            String str = getString(R.string.item3_title);
////            Log.d(LOG_TAG, "...str=" + str);
////            // b5Toolbar.setTitle(str); // NOT WORKING
////            game_mode = 3;
////
////            getSupportActionBar().setTitle(str);
////        }
//
//
//        txtStatus = (TextView) findViewById(R.id.txtStatus);
//        txtDebug1 = (TextView) findViewById(R.id.txtDebug1);
//        txtDebug2 = (TextView) findViewById(R.id.txtDebug2);
//
//        txtDebug1.setBackgroundColor(color400);
//        txtDebug2.setBackgroundColor(color300);
//
//
//        Random rnd = new Random();
//        checkedList1 = new ArrayList<>();
//        buttons = new ArrayList<Button>();
//        for (int id : BUTTON_IDS) {
//            Button button = (Button) findViewById(id);
//            button.setOnClickListener(this); // maybe
//            button.setTextAppearance(this, android.R.style.TextAppearance_Large);
//            buttons.add(button);
//        }
//        for (int i = 0; i < 25; i++) {
//            buttons.get(i).setText("" + i);
//        }
//        resetBtnBackground();
//    }
//
//
//    //    @Override
////    public void onConfigurationChanged(Configuration newConfig) {
////        super.onConfigurationChanged(newConfig);
////
////        // Checks the orientation of the screen
////        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
////
////            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
////            setContentView(R.layout.activity_btn_cnt_lines_land);
////
////
////        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
////
////            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
////            setContentView(R.layout.activity_btn_cnt_lines);
////        }
////    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game1, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent i = new Intent(this, SettingsActivity.class);
//            startActivity(i);
//            return true;
//        }

        if (id == R.id.action_question) {
            showSimpleMsg("What to do?","(1)Click <- to Game Menu\n(2)Click on Number to play \n(3)Reset game");
            return true;
        }
//
//        if (id == R.id.home) {
//            NavUtils.navigateUpFromSameTask(this);
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }


    public static int[] getIntArray(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }


    private boolean isOnTheList(int val, List<Integer> list) {
        for (Integer x : list) {
            if (x == val) {
                ;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        Log.d(LOG_TAG,"!!!onClick "+view.toString());

        if (isGameOver) {
            showGameOver();
            return;
        }


        if (isChoosingSet) {
            resetGame();
            return;
        }

        // for game mode



        if (whoseTurn != humanTurn) {
            showSimpleMsg("Android's Turn Now", "Please wait for Android to finish this move, then your turn!");

            return;
        }


        int index1 = view.getId() - R.id.btn0;
        Log.d(LOG_TAG,"!!!onClick index1="+index1);


        if (isOnTheList(index1, checkedList1)) {
            return;
        }

        if (textBoardStatus == TEXT_BOARD_STATUS_1) {
            textBoardStatus = TEXT_BOARD_STATUS_2;
        }


        humanPlay(index1);
    }

    private void humanPlay(int index1) {
        // 1. get P1's move and add it to checked list
        buttons.get(index1).setBackgroundColor(color400);
        buttons.get(index1).setPaintFlags(buttons.get(index1).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
        checkedList1.add(index1);

        // 2. add it to opponent's checked list
        int val1 = valArr1[index1];
        int index2 = b2.getPosition(val1);
        checkedList2.add(index2);

        // 3. show both text board
        showTextBoard();

        // 4. change Turn
        checkGameOver();
        if (isGameOver) {
            showGameOver();
        } else {
            whoseTurn = androidTurn;
            androidPlay();
        }
    }

    //    private class AndroidPlayTask extends AsyncTask<URL, Integer, Long> {
//        protected Long doInBackground(URL... urls) {
//
//            return 1L;
//        }
//
//        protected void onProgressUpdate(Integer... progress) {
//          //  setProgressPercent(progress[0]);
//        }
//
//        protected void onPostExecute(Long result) {
//
//        }
//    }
    private void showSimpleMsg(String title, String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showGameOver() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Game Over")
                .setMessage(gameOverMsg)
                .setPositiveButton("OK", null)
                .show();
    }

    private void checkGameOver() {
        Board5x5 lineCounter1 = new Board5x5Counter();
        Board5x5 lineCounter2 = new Board5x5Counter();
        int[] arr1 = getIntArray(checkedList1);
        int[] arr2 = getIntArray(checkedList2);
        lineCounter1.setChecked(arr1);
        lineCounter2.setChecked(arr2);
        int lineCnt1 = lineCounter1.getLineCount();
        int lineCnt2 = lineCounter2.getLineCount();
        if (lineCnt1 >= 3 || lineCnt2 >= 3) {
            isGameOver = true;
            if (lineCnt1 > lineCnt2) {
                gameOverMsg = "Winner is Human!";
            }
            if (lineCnt1 < lineCnt2) {
                gameOverMsg = "Winner is Android!";
            }
            if (lineCnt1 == lineCnt2) {
                gameOverMsg = "Draw Game!";
            }
        }
    }

    /**
     * just valid, for draft only
     *
     * @return
     */
    private int getOneValidAndroidMove() {


        int result = -1;

        Random r2 = new Random();
        for (int i = 0; i < 1000; i++) {
            result = r2.nextInt(25);// get 0,1,2,...,23,24
            if (!isOnTheList(result, checkedList2)) {
                break;
            }
        }

        return result;
    }

    private int getOneAndroidMove() {


        int move = 12; // the center is a must

        if (!isOnTheList(move, checkedList2)) {
            return move;
        }


        int maxAddedVal = 0;
        int maxAddedValMove = 0;
        Random r2 = new Random();

        // consider invalid try and to make it in random style, not always by the sequence
        //
        int validCnt = 0;

        for (int i = 0; i < 100; i++) {
            move = r2.nextInt(25);// get 0,1,2,...,23,24
            if (!isOnTheList(move, checkedList2)) {
                validCnt++;

                List<Integer> temp = new ArrayList<>(checkedList2);
                temp.add(move);


                Board5x5 lineCounter2 = new Board5x5Counter();
                int[] arr2 = getIntArray(temp);
                lineCounter2.setChecked(arr2);
                int tmpVal = lineCounter2.getAddedValue();

//                Log.d(LOG_TAG, "validCnt is " + validCnt);
//                Log.d(LOG_TAG, "...try move is " + move);
//                Log.d(LOG_TAG, "...addedVal " + tmpVal);


                if (tmpVal > maxAddedVal) {
                    maxAddedVal = tmpVal;
                    maxAddedValMove = move;
//                    Log.d(LOG_TAG, "@@@maxAddedVal is " + maxAddedVal);
//                    Log.d(LOG_TAG, "@@@maxAddedValMove is " + move);


                }

                //break;
            }
        }

        return maxAddedValMove;
    }

    private void showTextBoard() {
//        Log.d(LOG_TAG, "...showTextBoard textBoardStatus=" + textBoardStatus);
// compute line count and display it

        switch (game_mode) {
            case 1:
                showTextBaordGameMode1();
                break;

            case 2:
                showTextBaordGameMode2();
                break;
            case 3:
                break;
        }

    }

    private void showTextBaordGameMode1() {

        Board5x5 lineCounter1 = new Board5x5Counter();
        Board5x5 lineCounter2 = new Board5x5Counter();
        int[] arr1 = getIntArray(checkedList1);
        int[] arr2 = getIntArray(checkedList2);
        lineCounter1.setChecked(arr1);
        lineCounter2.setChecked(arr2);
        //
        lineCounter1.setValueSet(valArr1);
        lineCounter2.setValueSet(valArr2);


        int lineCnt1 = lineCounter1.getLineCount();
        int lineCnt2 = lineCounter2.getLineCount();
        String title1 = "Human\n";
        String title2 = "Android\n";

        if (textBoardStatus == TEXT_BOARD_STATUS_2) {
            txtDebug1.setText(title1 + lineCounter1.getTextBoard(Board5x5.STYLE_1));
            txtDebug2.setText(title2 + lineCounter2.getTextBoard(Board5x5.STYLE_1));
            txtDebug1.setVisibility(View.VISIBLE);
            txtDebug2.setVisibility(View.VISIBLE);
        }
        if (textBoardStatus == TEXT_BOARD_STATUS_3) {
            txtDebug1.setText(title1 + lineCounter1.getTextBoard(Board5x5.STYLE_2));
            txtDebug2.setText(title2 + lineCounter2.getTextBoard(Board5x5.STYLE_2));
            txtDebug1.setVisibility(View.VISIBLE);
            txtDebug2.setVisibility(View.VISIBLE);
        }
    }

    private void showTextBaordGameMode2() {

        Board5x5 lineCounter1 = new Board5x5Counter();
        Board5x5 lineCounter2 = new Board5x5Counter();
        int[] arr1 = getIntArray(checkedList1);
        int[] arr2 = getIntArray(checkedList2);
        lineCounter1.setChecked(arr1);
        lineCounter2.setChecked(arr2);
        //
        lineCounter1.setValueSet(valArr1);
        lineCounter2.setValueSet(valArr2);


        int lineCnt1 = lineCounter1.getLineCount();
        int lineCnt2 = lineCounter2.getLineCount();
        String title1 = "Human\n";
        String title2 = "Android\n";

        if (textBoardStatus == TEXT_BOARD_STATUS_2) {
            txtDebug2.setText(title1 + lineCounter1.getTextBoard(Board5x5.STYLE_1));
            txtDebug1.setText(title2 + lineCounter2.getTextBoard(Board5x5.STYLE_1));
            txtDebug1.setVisibility(View.VISIBLE);
            txtDebug2.setVisibility(View.VISIBLE);
        }
        if (textBoardStatus == TEXT_BOARD_STATUS_3) {
            txtDebug2.setText(title1 + lineCounter1.getTextBoard(Board5x5.STYLE_2));
            txtDebug1.setText(title2 + lineCounter2.getTextBoard(Board5x5.STYLE_2));
            txtDebug1.setVisibility(View.VISIBLE);
            txtDebug2.setVisibility(View.VISIBLE);
        }
    }

    private void androidPlay() {
        if (isGameOver) {
            return;
        }

        // 1. get P2's move and add it to checked list
        int index2 = getOneAndroidMove();
        checkedList2.add(index2);

        // 2. add it to opponent's checked list
        int val2 = valArr2[index2];
        final int index1 = b1.getPosition(val2);
        checkedList1.add(index1);

   SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // Retrieves a string value for the preferences. The second parameter
        // is the default value to use if a preference value is not found.
        final String str = sharedPrefs.getString("android_speed", "1");
//        final String str = "3";

        int intSpeed = Integer.valueOf(str);
        final int adjSpeed = 100 * (4 - intSpeed); // 3->1, 2->2,1->3


        new AsyncTask<String, Long, String>() {

            @Override
            protected String doInBackground(String... params) {


                Random r = new Random();
                int ms = r.nextInt(adjSpeed);
                try {
                    Thread.sleep(200 + ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showTextBoard();
                //    also show on P1's main board
                buttons.get(index1).setBackgroundColor(color300);
//                buttons.get(index1).setBackgroundColor(getResources().getColor(R.color.b5ColorAccent));


                buttons.get(index1).setPaintFlags(buttons.get(index1).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


                // 4. change Turn
                checkGameOver();
                if (isGameOver) {
                    showGameOver();
                } else {
                    whoseTurn = humanTurn;
                }

            }
        }.execute(null, null, null);

        // 3. show both text board
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    private void resetBtnBackground() {
        for (int i = 0; i < 25; i++) {
//            buttons.get(i).setBackgroundColor(color300);
            buttons.get(i).setBackgroundColor(Color.WHITE);

            //http://stackoverflow.com/questions/6054562/how-to-make-the-corners-of-a-button-round
//            buttons.get(i).setBackground(getResources().getDrawable(R.drawable.mybtn));

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        //textBoardStatus=TEXT_BOARD_STATUS_CHOOSE_SET_HIDE;
        resetGame();
    }

    public void onClickBtnReset(View view) {

        resetGame();
        //    showTextBoard();
    }

    //TODO
    // It's possible previous move by Android was not yet complete.
    // So if reset game, possible just come out unwanted move from Android
    //
    private void resetGame() {
        if (!isGameOver) {
            switch (game_mode) {
                case 1:
                    if (whoseTurn != humanTurn) {
                        showSimpleMsg("Android's Turn Now", "Please wait for Android to finish this move, then to Reset!");

                        return;
                    }

                    isChoosingSet = true;
                    break;
                case 2:
                    //isChoosingSet=false;
                    isChoosingSet = true;

                    break;
                case 3:
                    //isChoosingSet=false;
                    break;


            }
        }
        btnStart.setVisibility(View.VISIBLE);
        resetBtnBackground();
        checkedList1 = new ArrayList<>();
        checkedList2 = new ArrayList<>();

        txtStatus.setText("");
        b1 = new B253();
        b2 = new B253();

        b1.makeRandomSet();
        b2.makeRandomSet();

        valArr1 = b1.getValArray();
        valArr2 = b2.getValArray();

        for (int i = 0; i < 25; i++) {
            buttons.get(i).setPaintFlags(0);
            buttons.get(i).setText("" + valArr1[i]);
//            buttons.get(i).setText(Html.fromHtml("<del>99</del>"));

        }

//        txtDebug1.setText("");
        txtDebug1.setVisibility(View.GONE);
        txtDebug2.setVisibility(View.GONE);
//        txtDebug3.setVisibility(View.VISIBLE);


        Board5x5 lineCounter2 = new Board5x5Counter();
        int[] arr2 = getIntArray(checkedList2);
        lineCounter2.setChecked(arr2);
        lineCounter2.setValueSet(valArr2);

        String title2 = "Android\n";


//        txtDebug3.setText(title2 + b2.toString());
//        txtDebug3.setText(title2 + lineCounter2.getTextBoard(Board5x5.STYLE_2));

        // Game's Turn
        whoseTurn = humanTurn;
        isGameOver = false;
        textBoardStatus = TEXT_BOARD_STATUS_1;

    }

    private void switchTextBoardStatus() {

        if (textBoardStatus == TEXT_BOARD_STATUS_CHOOSE_SET_SHOW) {
            textBoardStatus = TEXT_BOARD_STATUS_CHOOSE_SET_HIDE;
            Log.d(LOG_TAG, "show to hide...");
            return;
        }
        if (textBoardStatus == TEXT_BOARD_STATUS_CHOOSE_SET_HIDE) {
            textBoardStatus = TEXT_BOARD_STATUS_CHOOSE_SET_SHOW;
            Log.d(LOG_TAG, " hide to show.......");
            return;
        }


        if (textBoardStatus == TEXT_BOARD_STATUS_2) {
            textBoardStatus = TEXT_BOARD_STATUS_3;
            return;
        }
        if (textBoardStatus == TEXT_BOARD_STATUS_3) {
            textBoardStatus = TEXT_BOARD_STATUS_2;
            return;
        }


    }


    public void onClineTextBoard(View view) {
        // if ()
        switchTextBoardStatus();
        showTextBoard();
    }

    public void onClickBtnStart(View view) {

    }
}
