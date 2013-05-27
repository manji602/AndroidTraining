package jp.mixi.assignment.test.target.uiautomator;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class MainActivityUIAutomator  extends UiAutomatorTestCase {
	public void testMainActivity() throws Exception {
		// デバイスオブジェクトの取得。このオブジェクトを介して、デバイスの状態を取得したり、UI の操作を行ったりする。
        UiDevice device = getUiDevice();
        // ホームボタンを押す
        device.pressHome();
        // ホームボタンに有るターゲットのアイコンをタップする
        UiObject launchIcon = new UiObject(new UiSelector().textContains("TestAssignmentTarget"));
        launchIcon.clickAndWaitForNewWindow();
        
        // 起動した（指定したパッケージ名のアプリがフォアグラウンドに居て、オブジェクトの取得が無事に出来る）
        UiObject app = new UiObject(new UiSelector().packageName("jp.mixi.assignment.test.target"));
        assertTrue(app.exists());
        
        // EditText, Buttonの取得
        UiObject editText = new UiObject(new UiSelector().className("android.widget.EditText"));
        UiObject button = new UiObject(new UiSelector().className("android.widget.Button"));
        
        //buttonが押せることを確認
        editText.setText("KeithYokoma");
        assertTrue(button.isClickable());
        
        //buttonが押せないことを確認
        editText.setText("KeithYokomaHogeHoge");
        assertFalse(button.isClickable());
	}
}
