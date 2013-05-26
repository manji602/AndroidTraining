package jp.mixi.assignment.contentprovider.practice1;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.contentProviderSelect);
        
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView resultText = (TextView)findViewById(R.id.resultTextView);
                String resultString = new String();
                Uri uri = Uri.parse("content://" + "jp.mixi.sample.contentprovider.Book" + "/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    String publisher = cursor.getString(cursor.getColumnIndexOrThrow("publisher"));
                    String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
                    //TextViewで改行したいのですがうまくいかないです…
                    resultString = resultString + title + " , " + publisher + " , " + price + " ";
                }
                // 処理が完了したらCursorを閉じます
                cursor.close();
                resultText.setText(resultString);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
