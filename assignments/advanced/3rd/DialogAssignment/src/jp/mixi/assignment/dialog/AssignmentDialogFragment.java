package jp.mixi.assignment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AssignmentDialogFragment extends DialogFragment {
    private Callbacks mCallbacks;
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCallbacks = (Callbacks)getActivity();
        View view = inflater.inflate(R.layout.dialog_content, container, false);
        Button okButton = (Button) view.findViewById(R.id.okButton);
        Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.setText(mCallbacks.getName());
        
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = editText.getText().toString();
                mCallbacks.setName(name);
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
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
    
    public interface Callbacks {
        String getName();
        void setName(String name);
    }
}