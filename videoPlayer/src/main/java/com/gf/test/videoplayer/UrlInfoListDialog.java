/**
 * 
 */
package com.gf.test.videoplayer;
import java.util.List;

import org.xutils.view.annotation.ViewInject;

import com.gf.test.videoplayer.R;

import android.content.Context;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UrlInfoListDialog extends AppBaseDialog implements OnItemClickListener{
	public interface CallBack{
		void onSelected(UrlInfo urlInfo);
	}
	private List<UrlInfo> mUrlInfos;
	private Context mContext;
	@ViewInject(R.id.list_urls)
	private ListView mListUrls;
	private CallBack mCallBack;
	public UrlInfoListDialog(Context context, List<UrlInfo> urlInfos, CallBack callBack) {
		super(context);
		mContext = context;
		mUrlInfos = urlInfos;
		mCallBack = callBack;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_urlinfo_list;
	}

	@Override
	public void initData() {
		//new UrlInfoAdatper(mContext, android.R.layout.simple_list_item_single_choice, android.R.id.text1, mUrlInfos)
		ArrayAdapter<UrlInfo> sortTypeAdapter = new UrlInfoAdatper(mContext, android.R.layout.simple_list_item_single_choice, android.R.id.text1, mUrlInfos);
		mListUrls.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
		mListUrls.setAdapter(sortTypeAdapter);
	}

	@Override
	public void initEvent() {
		mListUrls.setOnItemClickListener(this);
	}

	@Override
	public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data,
			Menu menu, int deviceId) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mCallBack.onSelected(mUrlInfos.get(position));
		dismiss();
	}

}
