
package jp.mixi.practice.fragment.beg;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager manager = getSupportFragmentManager();

		//(1)レイアウトに記述
		Fragment fragment = manager.findFragmentById(R.id.MyFragment);

		//(2)FragmentManager と FragmentTransaction で管理
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.MyFrameLayout, SampleFragment.createInstance());
		transaction.replace(R.id.MyFrameLayout, SampleFragment.createInstance());
		transaction.commit();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
