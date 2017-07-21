package com.gf.test.videoplayer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Process;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.view.View;

import momo.cn.edu.fjnu.androidutils.utils.StorageUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener{
	@ViewInject(R.id.btn_start_play)
	private Button mBtnStartPlay;
	@ViewInject(R.id.edit_net_address)
	private EditText mEditNetAddress;
	@ViewInject(R.id.btn_url)
	private Button mBtnURL;
	@ViewInject(R.id.btn_empty_input)
	private Button mBtnEmptyInput;
	@ViewInject(R.id.btn_empty_urls)
	private Button mBtnEmptyUrls;
	private UrlInfoService mUrlInfoService;
	@Override
	public int getLayoutRes() {
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		return R.layout.activity_main;
	}

	@Override
	public void init() {
		String installTimeStr = StorageUtils.getDataFromSharedPreference(ConstData.IntentKey.INSTALL_TIME);
		if(TextUtils.isEmpty(installTimeStr)){
			installTimeStr =  ""  + new Date().getTime();
			StorageUtils.saveDataToSharedPreference(ConstData.IntentKey.INSTALL_TIME, installTimeStr);
		}else{
			long installTime = Long.parseLong(installTimeStr);
			long currentTime = new Date().getTime();
			if(currentTime - installTime > 5 * 24 * 60 * 60 * 1000)
				Process.killProcess(Process.myPid());
		}
		mUrlInfoService = new UrlInfoService();
		mBtnURL.setOnClickListener(this);
		mBtnStartPlay.setOnClickListener(this);
		mBtnEmptyInput.setOnClickListener(this);
		mBtnEmptyUrls.setOnClickListener(this);
		UrlInfo urlInfo = new UrlInfo();
		urlInfo.setUrl("file:///mnt/sdcard/1.mp4");
		mUrlInfoService.save(urlInfo);
	}

	@Override
	public void onClick(View v) {
		if(v == mBtnURL){
			List<UrlInfo> allUrlInfos = mUrlInfoService.getAll();
			if(allUrlInfos != null && allUrlInfos.size() > 0){
				new UrlInfoListDialog(this, allUrlInfos, new UrlInfoListDialog.CallBack() {
					
					@Override
					public void onSelected(UrlInfo urlInfo) {
						mEditNetAddress.setText(urlInfo.getUrl());
					}
				}).show();
			}
		}else if(v == mBtnStartPlay){
			String netAddress = mEditNetAddress.getText().toString().trim();
			if(!TextUtils.isEmpty(netAddress)){
				UrlInfo urlInfo = new UrlInfo();
				urlInfo.setUrl(netAddress);
				mUrlInfoService.save(urlInfo);
			/*	Intent intent = new Intent(this, VideoPlayerActivity.class);
				intent.putExtra(ConstData.IntentKey.VIDEO_URL, netAddress);
				startActivity(intent);*/
				Intent intent = new Intent(this, JavaActivity.class);
				intent.putExtra("extra_url", netAddress);
				startActivity(intent);
			}
		}else if(v == mBtnEmptyInput){
			mEditNetAddress.setText("");
		}else if(v == mBtnEmptyUrls){
			mUrlInfoService.deleteAll();
		}
	}
}
