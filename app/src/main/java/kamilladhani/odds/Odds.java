package kamilladhani.odds;

import android.content.Context;
import android.content.DialogInterface;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class Odds extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odds);

        final EditText odds = (EditText) findViewById(R.id.oddsBox);
        odds.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            Button submit = (Button) findViewById(R.id.oddsSet);
                            submit.performClick();
                            return true;
                    }
                }
                return false;
            }
        });

        odds.setOnClickListener(new View.OnClickListener() {

            String name = odds.getText().toString();
            String origVal = getString(R.string.oddsBoxDef);

            @Override
            public void onClick(View v) {
                if (name.equals(origVal)) {
                    odds.setText("");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odds, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOddsSet (View v) {
        TextView oddsStatement = (TextView) findViewById(R.id.oddsStatement);
        EditText odds = (EditText) findViewById(R.id.oddsBox);
        String oddsStr = odds.getText().toString();

        // Make sure that a valid number was entered
        try {
            int oddsInt = Integer.parseInt(oddsStr);
            if (oddsInt <= 1) {
                throw new NumberFormatException();
            }
            final Random rand = new Random();
            int yourInt = rand.nextInt(oddsInt) + 1;
            oddsStatement.setText(getString(R.string.oddsStatementPre) + " " + String.valueOf(yourInt));
        } catch (NumberFormatException nfe) {
            oddsStatement.setText(getString(R.string.oddsStatementWarn));
        }

        // Hide Keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
