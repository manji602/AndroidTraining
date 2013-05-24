package jp.mixi.assignment.test.target;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements TextWatcher{
	Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.VerifyButton);
        EditText text = (EditText) findViewById(R.id.IdentityEditor);
        button.setEnabled(false);
        
        text.addTextChangedListener(this);
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,int after) {
    }
 
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
 
    @Override
    public void afterTextChanged(Editable s) {
    	TextView test = (TextView) findViewById(R.id.test);
    	test.setText(s.toString());
    	if (s.toString().equals("KeithYokoma") ) {
    		button.setEnabled(true);
    	} else {
    		button.setEnabled(false);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
