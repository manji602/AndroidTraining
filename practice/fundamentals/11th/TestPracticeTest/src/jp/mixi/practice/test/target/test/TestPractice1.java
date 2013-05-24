package jp.mixi.practice.test.target.test;

import jp.mixi.practice.test.target.SubActivity;
import jp.mixi.practice.test.target.TestTarget1;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

public class TestPractice1 extends AndroidTestCase{
	// テストの前準備のメソッド。テストケースの実行ごとに呼ばれる。
    // 事前準備中に何らかの例外が起こる可能性があるので、例外をスローする宣言をする。
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    // テストの後始末のメソッド。テストケースの実行ごとに呼ばれる。
    // 後始末中に何らかの例外が起こる可能性があるので、例外をスローする宣言をする。
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // テストケース本体。名前は必ず test から始まる
    // テスト中に異常が発生した場合（=例外がスローされた場合）、テストを Fail とするためその例外をそのまま投げるようにする
    public void testStartSubActivity() throws Exception {
    	TestTarget1 target = new TestTarget1();
    	// AndroidTestCase が持っている ActivityContext ではなく、自分でモックした Context
        target.startSubActivity(new TestTarget1Context(getContext()), "title");
    }
    
    public void testIsValidIntent() throws Exception {
    	TestTarget1 target = new TestTarget1();
    	
    	//true
    	Intent intent1 = new Intent();
    	intent1.putExtra(Intent.EXTRA_TEXT, "extra text");
    	intent1.setData(Uri.parse("http://mixi.jp"));
    	assertTrue(target.isValidIntent(intent1));

    	//false
    	Intent intent2 = new Intent();
    	intent2.setData(Uri.parse("http://mixi.jp"));
    	assertFalse(target.isValidIntent(intent2));
    	
    	//false
    	Intent intent3 = new Intent();
    	intent3.putExtra(Intent.EXTRA_TEXT, "extra text");
    	assertFalse(target.isValidIntent(intent3));
    }
    

    
 // Context#startActivity() が、期待通りのコンポーネントに Intent を投げているかテストするための
    // MockContext
    private static class TestTarget1Context extends MockContext {
        private Context mContext;

        public TestTarget1Context(Context baseContext) {
            mContext = baseContext;
        }

        @Override
        public String getPackageName() {
            return mContext.getPackageName();
        }

        @Override
        public void startActivity(Intent intent) {
            // Intent から、Intent の送り先のコンポーネント情報を取り出して、期待値と一致するか確認する
            ComponentName component = intent.getComponent();
            assertEquals(SubActivity.class.getCanonicalName(), component.getClassName());
            // Extra に期待するものが有るか確認する
            assertTrue(intent.hasExtra(Intent.EXTRA_SUBJECT));
            assertEquals("title", intent.getStringExtra(Intent.EXTRA_SUBJECT));
        }
    }
}
