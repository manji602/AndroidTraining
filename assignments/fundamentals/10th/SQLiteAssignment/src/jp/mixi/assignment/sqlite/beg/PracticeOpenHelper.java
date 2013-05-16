
package jp.mixi.assignment.sqlite.beg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PracticeOpenHelper extends SQLiteOpenHelper {

    @SuppressWarnings("unused")
    private static final String TAG = PracticeOpenHelper.class.getSimpleName();

    // データーベースのバージョン
    // データベーススキーマを変える場合は、バージョンを上げること
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "practice.db";

    private static final String BOOK_TABLE_CREATE =
            "CREATE TABLE " + Practice.TABLE_NAME + " (" +
                    Practice._ID + " INTEGER PRIMARY KEY," +
                    Practice.COLUMN_NAME + " TEXT NOT NULL, " +
                    Practice.COLUMN_VERSION + " TEXT);";

    private static final String BOOK_TABLE_DELETE =
            "DROP TABLE IF EXISTS " + Practice.TABLE_NAME;

    public PracticeOpenHelper(Context context) {
        // データベース名、バージョンを指定する
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        db.execSQL(BOOK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ここでアップデート条件を判定する
        db.execSQL(BOOK_TABLE_DELETE);
        onCreate(db);
    }

}
