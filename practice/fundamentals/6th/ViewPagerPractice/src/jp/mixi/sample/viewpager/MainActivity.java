package jp.mixi.sample.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ViewPager pager = (ViewPager) findViewById(R.id.Pager);
		TabAdapter tabAdapter = new TabAdapter(this, pager);
		pager.setAdapter(tabAdapter);
		
		tabAdapter.addTab("Tab1", SampleFragment.class, null);
		tabAdapter.addTab("Tab2", SampleFragment.class, null);
		tabAdapter.addTab("Tab3", SampleFragment.class, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
