
package jp.mixi.practice.sqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Insert).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        findViewById(R.id.Delete).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        findViewById(R.id.Update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        findViewById(R.id.Query).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void insert() {
        // TODO:ここにinsert処理を実装してください
    	BookOpenHelper bookOpenHelper = new BookOpenHelper(MainActivity.this);
    	// 書き込み用のSQLiteDatabaseを取得
    	SQLiteDatabase db = bookOpenHelper.getWritableDatabase();
    	ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "TITLE1");
        values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, "PUBLISHER1");
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1");

        // 戻り値はRowID（_ID）
        // エラーの場合は-1になる
        long rowId = db.insert(Book.BOOK_TABLE_NAME, null, values);
        if (rowId != -1){
        	Log.d("insert", "insert success.");
        } else {
        	Log.d("insert", "insert failed.");
        }
    }

    private void delete() {
        // TODO:ここにdelete処理を実装してください
    	BookOpenHelper bookOpenHelper = new BookOpenHelper(MainActivity.this);
    	SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };
        int deletedCount = db.delete(Book.BOOK_TABLE_NAME, selection, selectionArgs);
        if (deletedCount != -1){
        	Log.d("delete", "delete success");
        } else {
        	Log.d("delete", "delete failed.");
        }
    }

    private void update() {
        // TODO:ここにupdate処理を実装してください
    	BookOpenHelper bookOpenHelper = new BookOpenHelper(MainActivity.this);
    	SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        // update情報を設定する
        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, "NEW_TITLE");

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_TITLE + " LIKE ?";
        String[] selectionArgs = {
                "TITLE%"
        };

        int updatedCount = db.update(Book.BOOK_TABLE_NAME, values, selection, selectionArgs);
        if (updatedCount != -1){
        	Log.d("update", "update success.");
        } else {
        	Log.d("update", "update failed.");
        }
        
    }

    private void query() {
        // TODO:ここにquery処理を実装してください
    	BookOpenHelper bookOpenHelper = new BookOpenHelper(MainActivity.this);
    	// 読み込み用のSQLiteDatabaseを取得
        SQLiteDatabase db = bookOpenHelper.getReadableDatabase();

        // 取得する情報を指定
        String[] projection = {
                Book._ID,
                Book.COLUMN_NAME_BOOK_TITLE,
                Book.COLUMN_NAME_BOOK_PUBLISHER,
                Book.COLUMN_NAME_BOOK_PRICE
        };

        // 条件を指定
        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };

        Cursor cursor = db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.getCount() > 0){
        	boolean moveToFirst = cursor.moveToFirst();
        	long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(Book._ID));
        	Log.d("query", "select success. ID:" + itemId);
        } else {
        	Log.d("query", "select failed.");        	
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
