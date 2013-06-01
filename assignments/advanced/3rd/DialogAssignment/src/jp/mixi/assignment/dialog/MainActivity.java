
package jp.mixi.assignment.dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements AssignmentDialogFragment.Callbacks{
    MainActivity mActvity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_assignmentdialog).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showAssignmentDialog();
            }
        });
    }

    private void showAssignmentDialog() {
        // TODO:ダイアログを表示する処理を実装してください
    	DialogFragment myDialogFragment = new AssignmentDialogFragment();
    	myDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    	
        // 引数にFramentManagerとtagを設定します
        myDialogFragment.show(getSupportFragmentManager(), "my_dialog_fragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // TODO:独自DialogFragmentを実装してください
    // TODO:コンテンツ領域にはEditTextを配置した独自レイアウトを使用してください。また、そのためのレイアウトxmlを作成してください。
    
    public String getName() {
        String name = new String();
        SharedPreferences sp = getSharedPreferences("DialogAssignment", Context.MODE_PRIVATE);
        name = sp.getString("name", "");
        return name;
    }
    
    public void setName(String name) {
        SharedPreferences sp = getSharedPreferences("DialogAssignment", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("name", name);
        editor.commit();
    }
    

}
