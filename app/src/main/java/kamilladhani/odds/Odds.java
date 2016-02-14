package kamilladhani.odds;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Odds extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odds);

        final EditText odds = (EditText) findViewById(R.id.oddsBox);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_odds, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOddsSet(View v) {
        TextView oddsStatement = (TextView) findViewById(R.id.oddsStatement);
        EditText odds = (EditText) findViewById(R.id.oddsBox);
        String oddsStr = odds.getText().toString();

        try {
            int oddsInt = Integer.parseInt(oddsStr);
            if (oddsInt <= 1) {
                throw new NumberFormatException();
            }
            oddsStatement.setText(getString(R.string.oddsStatementPre) + " " + String.valueOf(oddsInt));
        } catch(NumberFormatException nfe) {
            oddsStatement.setText(getString(R.string.oddsStatementWarn));
        }

    }
}