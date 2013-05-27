
package jp.mixi.practice.preference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class MainActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener{
	private static final String AUTO_SYNC_INTERVAL_KEY = "auto_sync_interval_settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO:preferences.xmlを作成してください
        // TODO:作成したpreferences.xmlの読み込み処理を実装してください
        // TODO:ListPreferenceの設定が変更されたらSummaryに反映してください
        addPreferencesFromResource(R.xml.preferences);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(AUTO_SYNC_INTERVAL_KEY)) {
			ListPreference listPref = (ListPreference) findPreference(AUTO_SYNC_INTERVAL_KEY);
			listPref.setSummary(listPref.getValue());
		}
		
	}

}
