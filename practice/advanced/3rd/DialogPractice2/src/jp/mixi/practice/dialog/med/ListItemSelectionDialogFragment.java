package jp.mixi.practice.dialog.med;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * TODO: ダイアログで、はい・いいえ の選択肢を表示する
 * TODO: はい、を選択したら、リストの項目を削除する
 * @author keishin.yokomaku
 *
 */
public class ListItemSelectionDialogFragment extends DialogFragment {
    private int mPosition;
    private Callbacks mCallbacks;
    
    public ListItemSelectionDialogFragment(int position) {
    	mPosition = position;
    }
    
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	// AlertDialogはBuilderパターンで生成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mCallbacks = (Callbacks)getActivity();
        
        builder.setIcon(R.drawable.ic_launcher)
        .setTitle(R.string.title)
        .setMessage(R.string.message)
        .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				mCallbacks.deleteItemFromList(mPosition);
                Toast.makeText(getActivity(), "deleted", Toast.LENGTH_SHORT).show();
			}
		})
        .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity(), "canceled", Toast.LENGTH_SHORT).show();
            }
        });
        // Dialogを作成して返却
        return builder.create();
    }
    
    public interface Callbacks {
    	void deleteItemFromList(int position);
    }
}