
package jp.mixi.sample.viewpager;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SampleFragment extends SherlockFragment {

    @SuppressWarnings("unused")
    private static final String TAG = SampleFragment.class.getSimpleName();

    public static SampleFragment newInstance(int position) {

        SampleFragment sampleFragment = new SampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        sampleFragment.setArguments(bundle);

        return sampleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int position = 0;
        if (bundle != null) {
            position = bundle.getInt("position");
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView text = (TextView) view.findViewById(R.id.TextView1);
        text.setText(String.valueOf(position));

        return view;
    }
}
