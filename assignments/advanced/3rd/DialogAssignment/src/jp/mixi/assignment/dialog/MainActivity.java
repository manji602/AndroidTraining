
package jp.mixi.assignment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_assignmentdialog).setOnClickListener(new OnClickListener() {

            @Override
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
    public static class AssignmentDialogFragment extends DialogFragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dialog_content, container, false);
            Button okButton = (Button) view.findViewById(R.id.okButton);
            Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
            
            okButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.v("OK", "OK");
				}
			});
            cancelButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				}
			});
            return view;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
        	// Dialogを生成
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            return dialog;
        }

    }
}
