
package jp.mixi.assignment.intent.med;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View button = findViewById(R.id.CallActivityForResult);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO EditText と ボタンを 1 つずつ持つ新しい Activity を呼び出し、ボタンを押した時に結果を返すように実装する
                // TODO 返ってきた結果を Toast で表示するところも実装すること
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resCode, Intent intent){
        super.onActivityResult(requestCode, resCode, intent);
        if (requestCode == REQUEST_CODE){
            switch (resCode) {
            case Activity.RESULT_OK:
                String text = intent.getStringExtra("text");
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                break;
            case Activity.RESULT_CANCELED:
                break;
            }
        }
    }
}