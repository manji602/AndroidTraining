package jp.mixi.assignment.sqlite.beg;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PracticeAdapter extends ArrayAdapter<Practice>{
	private ArrayList<Practice> practices = new ArrayList<Practice>();
	private LayoutInflater inflater;
	private int layout;

	public PracticeAdapter(Context context, int resourceId){
		super(context, resourceId);
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layout = resourceId;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			view = this.inflater.inflate(this.layout, null);
		}
		
		Practice item = this.practices.get(position);
		((TextView)view.findViewById(R.id.id)).setText(Integer.toString(item.mId));
		((TextView)view.findViewById(R.id.name)).setText(item.mName);
		((TextView)view.findViewById(R.id.version)).setText(item.mVersion);
		return view;
	}
	public void add(Practice item){
		super.add(item);
		this.practices.add(item);
	}
}
