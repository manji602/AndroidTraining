
package jp.mixi.assignment.sqlite.beg;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO:独自SQLiteOpenHelperの作成、それに使用するカラム名などの定義
        // TODO:insert処理、query処理の実装
        insert("mixi", "1.0");
        insert("facebook", "1.0");
        insert("mobage", "2.0");
        insert("yahoo", "1.0");
        ArrayAdapter<Practice> adapter = new PracticeAdapter(MainActivity.this, R.layout.list);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        query(adapter);
    }

    private void insert(String name, String version) {
    	PracticeOpenHelper practiceOpenHelper = new PracticeOpenHelper(MainActivity.this);
    	// 書き込み用のSQLiteDatabaseを取得
    	SQLiteDatabase db = practiceOpenHelper.getWritableDatabase();
    	ContentValues values = new ContentValues();
        values.put(Practice.COLUMN_NAME, name); 
        values.put(Practice.COLUMN_VERSION, version);

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
    	if (name != null) {
    		long rowId = db.insert(Practice.TABLE_NAME, null, values);
    	}
    }
    
    private void query(ArrayAdapter<Practice> adapter) {
    	PracticeOpenHelper practiceOpenHelper = new PracticeOpenHelper(MainActivity.this);
    	// 読み込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = practiceOpenHelper.getReadableDatabase();
        // 取得する情報を指定
        String[] projection = {
                Practice._ID,
                Practice.COLUMN_NAME,
                Practice.COLUMN_VERSION
        };

        // 条件を指定
        String selection = Practice.COLUMN_VERSION + " = ?";
        String[] selectionArgs = {
                "1.0"
        };

        Cursor cursor = db.query(Practice.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.getCount() > 0){
        	if(cursor.moveToFirst()){
        		int length = cursor.getCount();
        		Toast.makeText(MainActivity.this, length + "", Toast.LENGTH_SHORT).show();
        		do{
        			int id = cursor.getInt(cursor.getColumnIndex(Practice._ID));
        			String name = cursor.getString(cursor.getColumnIndex(Practice.COLUMN_NAME));
        			String version = cursor.getString(cursor.getColumnIndex(Practice.COLUMN_VERSION));
        			Practice item = new Practice(id, name, version);
        			adapter.add(item);
        		} while(cursor.moveToNext());
        	}
        }
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
