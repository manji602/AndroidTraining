
package jp.mixi.practice.res.string.beg;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //locale ENGLISH
        Locale locale = Locale.ENGLISH;
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = getBaseContext().getResources();
        resources.updateConfiguration(config, null);
        setContentView(R.layout.activity_main);
        
        //monday
        String mondays = getResources().getQuantityString(R.plurals.monday, 2);
        TextView textviewMonday = (TextView)findViewById(R.id.monday);
        textviewMonday.setText(mondays);
        
        //tuesday
        String formattedTuesday = getString(R.string.tuesday, 5, 7);        
        TextView textviewTuesday = (TextView)findViewById(R.id.tuesday);
        textviewTuesday.setText(formattedTuesday);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
