package com.gf.test.videoplayer;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

public class UrlInfoAdatper extends ArrayAdapter<UrlInfo>{

	public UrlInfoAdatper(Context context, int resource,
			int textViewResourceId, List<UrlInfo> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = super.getView(position, convertView, parent);
		CheckedTextView checkedTextView = (CheckedTextView)itemView;
		checkedTextView.setTextColor(Color.WHITE);
		return itemView;
	}
}
