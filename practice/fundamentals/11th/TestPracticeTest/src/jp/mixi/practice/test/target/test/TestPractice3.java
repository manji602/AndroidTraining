package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.R;
import jp.mixi.practice.test.target.TestTarget3;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;


public class TestPractice3 extends ActivityInstrumentationTestCase2<TestTarget3>{	
	public TestPractice3() {
		this(TestTarget3.class);
	}
	
	public TestPractice3(Class<TestTarget3> activityClass) {
		super(activityClass);
	}
		
	public void testTextChangeScenario() throws Exception {
        Activity activity = getActivity();
        final EditText titleEditor = (EditText) activity.findViewById(R.id.TitleEditor);
        final EditText contentEditor = (EditText) activity.findViewById(R.id.ContentEditor);
        final TextView titleCounter = (TextView) activity.findViewById(R.id.TitleCounter);
        final TextView contentCounter = (TextView) activity.findViewById(R.id.ContentCounter);

        //initial state
        assertEquals("", titleEditor.getText().toString());
        assertEquals("", contentEditor.getText().toString());
        assertEquals("", titleCounter.getText().toString());
        assertEquals("", contentCounter.getText().toString());
        
        activity.runOnUiThread(new Runnable(){
			public void run() {    
		        titleEditor.setText("this is test title.");
		        assertEquals(titleCounter.getText().toString(), "19 / 10");

		        contentEditor.setText("this is test content.");
		        assertEquals(contentCounter.getText().toString(), "20 / 10000");
			}
        });
    }

}
