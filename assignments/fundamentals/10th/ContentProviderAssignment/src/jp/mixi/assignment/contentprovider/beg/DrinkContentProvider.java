
package jp.mixi.assignment.contentprovider.beg;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DrinkContentProvider extends ContentProvider {

    private DrinkOpenHelper mDrinkOpenHelper;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(Drink.AUTHORITY, Drink.DRINK_TABLE_NAME, Drink.DRINK);
    }
    @SuppressWarnings("unused")
    private static final String TAG = DrinkContentProvider.class.getSimpleName();

    // アプリケーション起動時にメインスレッド上で呼ばれます。そのため、時間がかかる処理は行うのは禁止されています。
    // ここで必要な初期化処理を行います。
    @Override
    public boolean onCreate() {
        mDrinkOpenHelper = new DrinkOpenHelper(getContext());
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        isValidUri(uri);

        SQLiteDatabase db = mDrinkOpenHelper.getWritableDatabase();
        int deletedCount = db.delete(Drink.DRINK_TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        isValidUri(uri);

        SQLiteDatabase db = mDrinkOpenHelper.getWritableDatabase();
        long rowId = db.insert(Drink.DRINK_TABLE_NAME, null, values);
        Uri insertedUri = ContentUris.withAppendedId(uri, rowId);
        // 設定したURIのデータに変更があったことを通知します
        getContext().getContentResolver().notifyChange(insertedUri, null);
        return insertedUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        isValidUri(uri);

        SQLiteDatabase db = mDrinkOpenHelper.getReadableDatabase();
        // URIからテーブル名を取得
        String tableName = uri.getPathSegments().get(0);
        Cursor cursor = db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
        // 設定したURIの変更を監視するように設定。registerContentObserverを使用するために必要。
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        isValidUri(uri);

        SQLiteDatabase db = mDrinkOpenHelper.getWritableDatabase();
        // URIからテーブル名を取得
        String tableName = uri.getPathSegments().get(0);
        int updatedCount = db.update(tableName, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updatedCount;
    }

    // このContentProviderで使用可能なURIかを判定します。
    // 使用不可の場合はIllegalArgumentExceptionを投げます。
    private void isValidUri(Uri uri) {
        if (URI_MATCHER.match(uri) != Drink.DRINK){
            throw new IllegalArgumentException("Unknown URI : " + uri);
        }
    }
}
