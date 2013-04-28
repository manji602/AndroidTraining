
package jp.mixi.assignment.controller.beg;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

/**
 * TODO: 課題1
 * {@link Toast#makeText(Context, CharSequence, int)} または
 * {@link Toast#makeText(Context, int, int)} を利用して、各ライフサイクルメソッドがどのような順番で
 * 実行されているかを確認してください。
 * 確認したら、assignments/fundamentals/2nd/Report.md にその順番を記述してください。
 * @author keishin.yokomaku
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }
    protected void onStart(){
    	super.onStart();
    	Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    protected void onResume(){
    	super.onResume();
    	Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    protected void onRestart(){
    	super.onRestart();
    	Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    protected void onPause(){
    	super.onPause();
    	Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    protected void onStop(){
    	super.onStop();
    	Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    protected void onDestroy(){
    	super.onDestroy();
    	Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
