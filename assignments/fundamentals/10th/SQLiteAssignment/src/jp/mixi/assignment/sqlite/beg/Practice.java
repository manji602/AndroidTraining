
package jp.mixi.assignment.sqlite.beg;

import android.provider.BaseColumns;

public class Practice implements BaseColumns {

    private static final String TAG = Practice.class.getSimpleName();

    public static final String TABLE_NAME = "android_code_name";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VERSION = "version";

    public int mId;
    public String mName;
    public String mVersion;

    public Practice(int id, String name, String version) {
        super();
        mId = id;
        mName = name;
        mVersion = version;
    }
}
