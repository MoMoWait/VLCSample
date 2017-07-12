package com.gf.test.videoplayer;

import org.xutils.view.annotation.ViewInject;

import com.gf.test.videoplayer.R;

import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoPlayerActivity extends BaseActivity{
	private static final String TAG = "VideoPlayerActivity";
	@ViewInject(R.id.video_play)
	private VideoView mVideoPlay;
	@ViewInject(R.id.progress_loading)
	private ProgressBar mProgresLoading;
	private String mUrl;
	
	@Override
	public int getLayoutRes() {
		return R.layout.activity_video_player;
	}

	@Override
	public void init() {
		mUrl = getIntent().getStringExtra(ConstData.IntentKey.VIDEO_URL);
		if(TextUtils.isEmpty(mUrl))
			finish();
		mVideoPlay.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.i(TAG, "onError");
				mProgresLoading.setVisibility(View.GONE);
				finish();
				return true;
			}
		});
		mVideoPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				Log.i(TAG, "onPrepared");
				mProgresLoading.setVisibility(View.GONE);
			}
		});
	}


	
	@Override
	protected void onResume() {
		super.onResume();
		playVideo();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mVideoPlay.pause();
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		try{
			mVideoPlay.stopPlayback();
		}catch (Exception e){
			
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			mProgresLoading.setVisibility(View.GONE);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void playVideo(){
		try{
			mVideoPlay.stopPlayback();
		}catch (Exception e){
			
		}
		mProgresLoading.setVisibility(View.VISIBLE);
		mVideoPlay.setVideoURI(Uri.parse(mUrl));
		mVideoPlay.start();
	}
	
}
