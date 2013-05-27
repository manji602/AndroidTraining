
package jp.mixi.practice.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_practice_dialog).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPracticeDialog();
            }
        });
    }

    private void showPracticeDialog() {
        // TODO:ダイアログを表示する処理を実装してください
    	DialogFragment myDialogFragment = new PracticeDialogFragment();
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
    public static class PracticeDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
        	// AlertDialogはBuilderパターンで生成
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.practice)
            .setMessage(R.string.message)
            .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "closed", Toast.LENGTH_SHORT).show();
                }
            });
            // Dialogを作成して返却
            return builder.create();
        }
    }
}
