package jp.mixi.assignment.contentprovider.beg;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
    private SimpleCursorAdapter mSimpleCursorAdapter;

    private ListView mListView;
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mListView = (ListView) findViewById(R.id.ListView);
        // UIにバインドするデータのカラム名
        String[] from = {
                Drink.COLUMN_NAME_DRINK_NAME, Drink.COLUMN_NAME_DRINK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Title, R.id.Price
        };

        // 第3引数のCursorはコールバックで設定されるのでnullを渡しています
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_drink, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);

        // ボタンクリックでインサート処理を実行
        Button button = (Button)findViewById(R.id.ADD);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insert();
            }
        });
        
     // ローダの管理をするオブジェクト
        LoaderManager loaderManager = getSupportLoaderManager();
        // ローダを初期化して非同期処理を開始する
        loaderManager.initLoader(0, null, this);
    }
    
    private void insert() {
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.clear();
            values.put(Drink.COLUMN_NAME_DRINK_NAME, "NAME" + i);
            values.put(Drink.COLUMN_NAME_DRINK_PRICE, "PRICE" + i);

            getContentResolver().insert(Drink.CONTENT_URI, values);
        }
    }
    
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        // TODO Auto-generated method stub
        return new CursorLoader(this, Drink.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        // TODO Auto-generated method stub
        mSimpleCursorAdapter.swapCursor(arg1);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        // TODO Auto-generated method stub
        mSimpleCursorAdapter.swapCursor(null);
    }

}
